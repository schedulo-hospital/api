package com.schedulo.datafetchers

import com.schedulo.generated.types.Organisation
import com.schedulo.generated.types.OrganisationInput
import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsQuery
import com.netflix.graphql.dgs.DgsMutation
import com.netflix.graphql.dgs.InputArgument
import com.netflix.graphql.dgs.exceptions.DgsBadRequestException
import com.schedulo.generated.types.User
import com.schedulo.models.OrganisationModel
import com.schedulo.models.OrganisationUserModel
import com.schedulo.models.Role
import com.schedulo.models.UserModel
import com.schedulo.repositories.OrganisationRepository
import com.schedulo.repositories.OrganisationUserRepository
import com.schedulo.repositories.UserRepository
import graphql.relay.Connection
import graphql.relay.SimpleListConnection
import graphql.schema.DataFetchingEnvironment
import org.bson.types.ObjectId
import org.springframework.security.core.context.SecurityContextHolder

@DgsComponent
class OrganizationDataFetcher(
        private val organisationRepository: OrganisationRepository,
        private val organisationUserRepository: OrganisationUserRepository,
        private val userRepository: UserRepository,
) {
    @DgsQuery
    suspend fun organisations(env: DataFetchingEnvironment): Connection<Organisation> {
        val user: User = SecurityContextHolder.getContext().authentication.principal as User

        val dbUser = userRepository.findById(user.id).get()
        val organisationsUsers = organisationUserRepository.findByUserId(dbUser)
        val organisations = organisationRepository.findAllById(organisationsUsers.map{ it.organisation.id.toString() }).toList()

        return SimpleListConnection(organisations.map { it ->
            Organisation(id = it.id.toString(), name = it.name, createdBy = it.createdBy.toString())
        }).get(env)
    }

    @DgsQuery
    suspend fun organisation(@InputArgument id : String): Organisation {
        val org = organisationRepository.findById(id)

        // TODO add check if user can read
        if (org.isEmpty) {
            throw DgsBadRequestException("Organisation does not exist")
        }

        return Organisation(id = org.get().id.toString(), name = org.get().name, createdBy = org.get().createdBy.toString())
    }

    @DgsMutation
    suspend fun createOrganisation(@InputArgument input : OrganisationInput): Organisation {
        val user: User = SecurityContextHolder.getContext().authentication.principal as User

        val organisation = organisationRepository.save(OrganisationModel(name = input.name, createdBy = ObjectId(user.id)))
        val dbUser = userRepository.findById(user.id).get()
        organisationUserRepository.save(OrganisationUserModel(organisation = organisation, user = dbUser, role = Role.Admin))

        return Organisation(id = organisation.id.toString(), name = organisation.name, createdBy = organisation.createdBy.toString())
    }

    @DgsMutation
    suspend fun organisationAddUser(@InputArgument organisationId : String, @InputArgument email : String): Organisation {
        val currentUser: User = SecurityContextHolder.getContext().authentication.principal as User

        val dbUser = userRepository.findById(currentUser.id).get()
        val organisation = organisationRepository.findById(organisationId).get()
        // check user is organisation Admin
        val organisationUser = organisationUserRepository.findByUserIdAndOrganisationId(dbUser, organisation)

        print(message = "Organisation user role: ${organisationUser.role}")
        if (organisationUser.role !== Role.Admin) {
            throw DgsBadRequestException("User is not an organisation admin.")
        }

        var user = userRepository.findByEmail(email)
        if (user == null) {
            user = userRepository.save(UserModel(email = email, password = "", name = "", registered = false)) as UserModel
        }

        organisationUserRepository.save(OrganisationUserModel(organisation = organisation, user = user, role = Role.User))

        return Organisation(id = organisation.id.toString(), name = organisation.name, createdBy = organisation.createdBy.toString())
    }
}