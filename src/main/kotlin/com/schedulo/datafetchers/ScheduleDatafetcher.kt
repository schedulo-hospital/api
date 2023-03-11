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

@DgsComponent
class ScheduleDataFetcher(
        private val scheduleRepository: ScheduleRepository,
        private val shiftRepository: ShiftRepository,
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
      val schedule = scheduleRepository.findById(id)
      return schedule.get()
  }

  protected fun save(schedule: ScheduleModel) {
    schedule.shifts?.forEach { shift ->
      val dbShift = shiftRepository.findById(shift.id).get()
      dbShift.user = shift.user

      shiftRepository.save(shift)
    }
  }
}