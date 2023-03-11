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
import com.schedulo.generated.types.AvailabilityInput
import com.schedulo.models.*
import com.schedulo.repositories.*
import com.schedulo.repositories.AvailabilityRepository
import graphql.relay.Connection
import graphql.relay.SimpleListConnection
import graphql.schema.DataFetchingEnvironment
import org.bson.types.ObjectId
import org.springframework.security.access.annotation.Secured
import org.springframework.security.core.context.SecurityContextHolder
import org.optaplanner.core.api.solver.SolverManager
import java.time.OffsetDateTime
import java.time.LocalDate

@DgsComponent
class AvailabilityDataFetcher(
        private val availablityRepository: AvailabilityRepository,
        private val userRepository: UserRepository,
) {
    @Secured(*arrayOf("ROLE_USER"))
    @DgsQuery
    suspend fun availabilities(@InputArgument from: LocalDate, @InputArgument to: LocalDate): List<AvailabilityModel> {
      return availablityRepository.findAllByDateBetween(from, to)
    }

    @Secured(*arrayOf("ROLE_USER"))
    @DgsMutation
    suspend fun setAvailability(@InputArgument input: AvailabilityInput): AvailabilityModel {
      // TODO check if user is admin when adding with input.userId take user from token otherwise
      val dbUser = userRepository.findById(input.userId).get()

      var availability = availablityRepository.findByUserAndDate(dbUser, input.date)
      if (availability != null) {
        availability.type = input.type
      } else {
        availability = AvailabilityModel(user = dbUser, type = input.type, date = input.date)
      }

      availablityRepository.save(availability)

      return availability
    }
}