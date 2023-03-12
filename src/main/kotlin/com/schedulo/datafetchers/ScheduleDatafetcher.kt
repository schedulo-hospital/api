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
import com.schedulo.models.*
import com.schedulo.repositories.*
import com.schedulo.repositories.ScheduleRepository
import com.schedulo.repositories.ShiftRepository
import graphql.relay.Connection
import graphql.relay.SimpleListConnection
import graphql.schema.DataFetchingEnvironment
import org.bson.types.ObjectId
import org.springframework.security.access.annotation.Secured
import org.springframework.security.core.context.SecurityContextHolder
import org.optaplanner.core.api.solver.SolverManager
import java.time.LocalDateTime

@DgsComponent
class ScheduleDataFetcher(
    private val scheduleRepository: ScheduleRepository,
    private val shiftRepository: ShiftRepository,
    private val availabilityRepository: AvailabilityRepository,
    private val userRepository: UserRepository,
    val solverManager: SolverManager<ScheduleModel, String>
) {
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

      return true
    }

    protected fun findById(id: String): ScheduleModel {
      var schedule = ScheduleModel()

      var availabilities = availabilityRepository.findAll()
      var users = userRepository.findAll()

      schedule.availablity = availabilities
      schedule.users = users
      schedule.shifts = listOf(
        ShiftModel(start = LocalDateTime.of(2023, 3, 11, 0, 0), end = LocalDateTime.of(2023, 3, 11, 23, 59), requiredSeniority = "JUNIOR", schedule = schedule),
        ShiftModel(start = LocalDateTime.of(2023, 3, 12, 0, 0), end = LocalDateTime.of(2023, 3, 12, 23, 59), requiredSeniority = "JUNIOR", schedule = schedule),
        ShiftModel(start = LocalDateTime.of(2023, 3, 13, 0, 0), end = LocalDateTime.of(2023, 3, 13, 23, 59), requiredSeniority = "JUNIOR", schedule = schedule),
        ShiftModel(start = LocalDateTime.of(2023, 3, 14, 0, 0), end = LocalDateTime.of(2023, 3, 14, 23, 59), requiredSeniority = "JUNIOR", schedule = schedule),
        ShiftModel(start = LocalDateTime.of(2023, 3, 15, 0, 0), end = LocalDateTime.of(2023, 3, 15, 23, 59), requiredSeniority = "JUNIOR", schedule = schedule),
        ShiftModel(start = LocalDateTime.of(2023, 3, 16, 0, 0), end = LocalDateTime.of(2023, 3, 16, 23, 59), requiredSeniority = "JUNIOR", schedule = schedule),
        ShiftModel(start = LocalDateTime.of(2023, 3, 17, 0, 0), end = LocalDateTime.of(2023, 3, 17, 23, 59), requiredSeniority = "JUNIOR", schedule = schedule),
        ShiftModel(start = LocalDateTime.of(2023, 3, 18, 0, 0), end = LocalDateTime.of(2023, 3, 18, 23, 59), requiredSeniority = "JUNIOR", schedule = schedule),
        ShiftModel(start = LocalDateTime.of(2023, 3, 19, 0, 0), end = LocalDateTime.of(2023, 3, 19, 23, 59), requiredSeniority = "JUNIOR", schedule = schedule),
        ShiftModel(start = LocalDateTime.of(2023, 3, 20, 0, 0), end = LocalDateTime.of(2023, 3, 20, 23, 59), requiredSeniority = "JUNIOR", schedule = schedule),
        ShiftModel(start = LocalDateTime.of(2023, 3, 21, 0, 0), end = LocalDateTime.of(2023, 3, 21, 23, 59), requiredSeniority = "JUNIOR", schedule = schedule),
        ShiftModel(start = LocalDateTime.of(2023, 3, 22, 0, 0), end = LocalDateTime.of(2023, 3, 22, 23, 59), requiredSeniority = "JUNIOR", schedule = schedule),
        ShiftModel(start = LocalDateTime.of(2023, 3, 23, 0, 0), end = LocalDateTime.of(2023, 3, 23, 23, 59), requiredSeniority = "JUNIOR", schedule = schedule),
      )
      // TODO move requiredSeniority to schedule

      return schedule
  }

  protected fun save(schedule: ScheduleModel) {
    println("Saving schedule: ${schedule.id}")
    println(schedule.score)
    schedule.shifts?.forEach { shift ->
      // val dbShift = shiftRepository.findById(shift.id).get()
      // dbShift.user = shift.user
      println(shift.start)
      println(shift.user?.name)
      // shiftRepository.save(shift)
    }
  }
}