package com.schedulo.datafetchers

import com.schedulo.generated.types.Organisation
import com.schedulo.generated.types.OrganisationInput
import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsQuery
import com.netflix.graphql.dgs.DgsMutation
import com.netflix.graphql.dgs.InputArgument
import com.netflix.graphql.dgs.exceptions.DgsBadRequestException
import com.schedulo.generated.types.Department
import com.schedulo.generated.types.User
import com.schedulo.generated.types.Schedule
import com.schedulo.generated.types.Seniority
import com.schedulo.generated.types.Shift
import com.schedulo.models.*
import com.schedulo.repositories.*
import com.schedulo.repositories.ScheduleRepository
import com.schedulo.repositories.ShiftRepository
import com.schedulo.repositories.DepartmentUserRepository
import graphql.relay.Connection
import graphql.relay.SimpleListConnection
import graphql.schema.DataFetchingEnvironment
import org.bson.types.ObjectId
import org.springframework.security.access.annotation.Secured
import org.springframework.security.core.context.SecurityContextHolder
import org.optaplanner.core.api.solver.SolverManager
import org.optaplanner.core.api.score.ScoreManager
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore
import java.time.LocalDateTime
import java.time.LocalDate

@DgsComponent
class ScheduleDataFetcher(
    private val scheduleRepository: ScheduleRepository,
    private val shiftRepository: ShiftRepository,
    private val availabilityRepository: AvailabilityRepository,
    private val userRepository: UserRepository,
    private val departmentUserRepository: DepartmentUserRepository,
    private val departmentRepository: DepartmentRepository,
    val solverManager: SolverManager<ScheduleModel, String>,
    val scoreManager: ScoreManager<ScheduleModel, HardSoftScore>
) {
    @Secured(*arrayOf("ROLE_USER"))
    @DgsQuery
    suspend fun schedules(env: DataFetchingEnvironment, @InputArgument departmentId: String): Connection<Schedule> {
        val user: User = SecurityContextHolder.getContext().authentication.principal as User
        // TODO check if user can see schedules - Admin only?

        val department = departmentRepository.findById(departmentId).get()
        val schedules = scheduleRepository.findByDepartment(department)

        return SimpleListConnection(schedules.map { it ->
            Schedule(id = it.id.toString(), start = LocalDate.of(it.start?.year as Int, it.start?.month, it.start?.dayOfMonth as Int), end = LocalDate.of(it.end?.year as Int, it.end?.month, it.end?.dayOfMonth as Int), name = it.name as String)
        }).get(env)
    }

    @Secured(*arrayOf("ROLE_USER"))
    @DgsQuery
    suspend fun schedule(@InputArgument id: String): Schedule {
        val user: User = SecurityContextHolder.getContext().authentication.principal as User
        // TODO check if user can see schedules - Admin only?

        val schedule = scheduleRepository.findById(id).get()

        return Schedule(id = schedule.id.toString(), start = LocalDate.of(schedule.start?.year as Int, schedule.start?.month, schedule.start?.dayOfMonth as Int), end = LocalDate.of(schedule.end?.year as Int, schedule.end?.month, schedule.end?.dayOfMonth as Int), name = schedule.name as String, score = schedule.solverScore, status = schedule.solverStatus.name)
    }

    @Secured(*arrayOf("ROLE_USER"))
    @DgsQuery
    suspend fun shifts(@InputArgument scheduleId: String): List<Shift> {
        val user: User = SecurityContextHolder.getContext().authentication.principal as User
        // TODO check if user can see schedules - Admin only?

        val shifts = shiftRepository.findAllBySchedule(ObjectId(scheduleId))

        return shifts.map { it ->
            Shift(
              id = it.id.toString(),
              start = LocalDate.of(it.start?.year as Int, it.start?.month, it.start?.dayOfMonth as Int),
              end = LocalDate.of(it.end?.year as Int, it.end?.month, it.end?.dayOfMonth as Int),
              requiredSeniority = Seniority.valueOf(it.requiredSeniority as String),
              user = it.userLoaded?.let { user -> User(id = user.id.toString(), name = user.userLoaded?.name as String, email = user.userLoaded?.email as String, seniority = user.seniority?.name) }
            )
        }
    }

    @Secured(*arrayOf("ROLE_USER"))
    @DgsMutation
    suspend fun createSchedule(@InputArgument departmentId: String, @InputArgument name: String, @InputArgument start: LocalDate, @InputArgument end: LocalDate): Schedule? {
      val department = departmentRepository.findById(departmentId).get()

      val schedule = scheduleRepository.save(ScheduleModel(name = name, start = start, end = end, department = department))
      var shifts = mutableListOf<ShiftModel>()

      // For each day between start and end
      generateSequence(start) { it.plusDays(1) }.takeWhile { it < end }.forEach {
        val seniorities = enumValues<Seniority>()
        for (seniority in seniorities) {
          val shift = ShiftModel(
            start = LocalDateTime.of(it.year, it.month, it.dayOfMonth, 0, 0),
            end = LocalDateTime.of(it.year, it.month, it.dayOfMonth, 23, 59),
            requiredSeniority = seniority.name,
            schedule = schedule.id
          )
          shifts.add(shift)
        }
      }

      shiftRepository.saveAll(shifts)
    
      return Schedule(id = schedule.id.toString(), start = schedule.start as LocalDate, end = schedule.end as LocalDate, name = schedule.name as String, status = schedule.solverStatus.name)
    }

    @Secured(*arrayOf("ROLE_USER"))
    @DgsMutation
    suspend fun startSolving(@InputArgument scheduleId: String): Boolean {
      solverManager.solveAndListen(scheduleId, this::findById, this::save)

      return true
    }

    @Secured(*arrayOf("ROLE_USER"))
    @DgsMutation
    suspend fun stopSolving(@InputArgument scheduleId: String): Boolean {
      solverManager.terminateEarly(scheduleId);

      var dbSchedule = scheduleRepository.findById(scheduleId).get()
      dbSchedule.solverStatus = solverManager.getSolverStatus(scheduleId)

      scheduleRepository.save(dbSchedule)

      return true
    }

    protected fun findById(id: String): ScheduleModel {
      var schedule = scheduleRepository.findById(id).get()

      val department = schedule.department

      var users = departmentUserRepository.findByDepartment(department?.id as ObjectId)

      schedule.availablity = availabilityRepository.findAllByUserId(users.map { it.user.toString() })
      schedule.users = users
      schedule.shifts = shiftRepository.findAllBySchedule(schedule.id)

      println("Loading schedule: ${schedule.id}")
      println(schedule.id)
      println(schedule.shifts?.size)
      println(schedule.users?.size)
      println(schedule.availablity?.size)
      return schedule
  }

  protected fun save(schedule: ScheduleModel) {
    println("Saving schedule: ${schedule.id}")
    println(schedule.score?.toString())

    var dbSchedule = scheduleRepository.findById(schedule.id.toString()).get()
    if (schedule.score != null) {
      dbSchedule.solverScore = schedule.score.toString()
    }

    dbSchedule.solverStatus = solverManager.getSolverStatus(schedule.id.toString())

    scheduleRepository.save(dbSchedule)

    var shifts = mutableListOf<ShiftModel>()
    schedule.shifts?.forEach { shift ->
      // TODO: Load multipe shifts at once
      val dbShift = shiftRepository.findById(shift.id.toString()).get()
      dbShift.user = shift.userLoaded?.id
      println(shift.start)
      println(shift.userLoaded?.userLoaded?.name)
      println(shift.userLoaded?.seniority.toString())
      println(shift.requiredSeniority.toString())
      shifts.add(dbShift)
    }

    shiftRepository.saveAll(shifts)
  }
}