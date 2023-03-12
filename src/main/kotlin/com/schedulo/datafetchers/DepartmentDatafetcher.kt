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
import com.schedulo.generated.types.Seniority
import com.schedulo.models.*
import com.schedulo.repositories.*
import graphql.relay.Connection
import graphql.relay.SimpleListConnection
import graphql.schema.DataFetchingEnvironment
import org.bson.types.ObjectId
import org.springframework.security.access.annotation.Secured
import org.springframework.security.core.context.SecurityContextHolder

@DgsComponent
class DepartmentDataFetcher(
        private val departmentRepository: DepartmentRepository,
        private val departmentUserRepository: DepartmentUserRepository,
        private val organisationUserRepository: OrganisationUserRepository,
        private val organisationRepository: OrganisationRepository,
        private val userRepository: UserRepository,
) {
    @Secured(*arrayOf("ROLE_USER"))
    @DgsQuery
    suspend fun departments(env: DataFetchingEnvironment, @InputArgument organisationId: String): Connection<Department> {
        val user: User = SecurityContextHolder.getContext().authentication.principal as User
        val dbUser = userRepository.findById(user.id).get()

        // does it really throw error if not found?
        val organisation = organisationRepository.findById(organisationId).get()
        val organisationUser = organisationUserRepository.findByUserIdAndOrganisationId(user = dbUser, organisation = organisation)
        val departmentUsers = departmentUserRepository.findByUserIdAndOrganisationId(user = dbUser, organisation = organisation)
        if (departmentUsers.isEmpty()) {
            throw DgsBadRequestException("User does not belong to any department in this organisation")
        }

        val departments = departmentRepository.findAllByOrganisation(organisation).toList()

        return SimpleListConnection(departments.map { it ->
            Department(id = it.id.toString(), name = it.name, createdBy = it.createdBy.toString())
        }).get(env)
    }

    @Secured(*arrayOf("ROLE_USER"))
    @DgsQuery
    suspend fun department(@InputArgument id : String): Organisation {
        val org = departmentRepository.findById(id)

        // TODO add check if user can read
        if (org.isEmpty) {
            throw DgsBadRequestException("Department does not exist")
        }

        return Organisation(id = org.get().id.toString(), name = org.get().name, createdBy = org.get().createdBy.toString())
    }

    @Secured(*arrayOf("ROLE_USER"))
    @DgsQuery
    suspend fun departmentUsers(env: DataFetchingEnvironment, @InputArgument id : String): Connection<User> {
        val user: User = SecurityContextHolder.getContext().authentication.principal as User
        val dbUser = userRepository.findById(user.id).get()

        val department = departmentRepository.findById(id).get()
        val departmentUser = departmentUserRepository.findByUserIdAndDepartmentId(user = dbUser, department = department)
        if (departmentUser.role !== Role.Admin) {
            throw DgsBadRequestException("You do not have permission to view users")
        }

        val departmentUsers = departmentUserRepository.findByDepartment(department).toList()
        return SimpleListConnection(departmentUsers.map { it ->
            User(id = it.user.id.toString(), email= it.user.email, name = it.user.name, seniority = it.seniority.toString())
        }).get(env)
    }

    @Secured(*arrayOf("ROLE_USER"))
    @DgsMutation
    suspend fun createDepartment(@InputArgument organisationId : String, @InputArgument name : String): Organisation {
        val user: User = SecurityContextHolder.getContext().authentication.principal as User
        val dbUser = userRepository.findById(user.id).get()

        val organisation = organisationRepository.findById(organisationId).get()
        val organisationUser = organisationUserRepository.findByUserIdAndOrganisationId(user = dbUser, organisation = organisation)
        if (organisationUser.role !== Role.Admin) {
            throw DgsBadRequestException("Organisation does not exist or you do not have permission to create departments")
        }

        val department = departmentRepository.save(DepartmentModel(name = name, organisation = organisation, createdBy = ObjectId(user.id)))
        departmentUserRepository.save(DepartmentUserModel(organisation = organisation, department = department, user = dbUser, role = Role.Admin, seniority = Seniority.JUNIOR))

        return Organisation(id = department.id.toString(), name = department.name, createdBy = department.createdBy.toString())
    }

    @Secured(*arrayOf("ROLE_USER"))
    @DgsMutation
    suspend fun departmentAddUser(@InputArgument departmentId : String, @InputArgument email : String, @InputArgument seniority: Seniority): Department {
        val currentUser: User = SecurityContextHolder.getContext().authentication.principal as User
        val dbUser = userRepository.findById(currentUser.id).get()

        // check user is department or organisation Admin
        // TODO handle exceptions
        val department = departmentRepository.findById(departmentId).get()
        val departmentUser = departmentUserRepository.findByUserIdAndDepartmentId(dbUser, department)
        val organisationUser = organisationUserRepository.findByUserIdAndOrganisationId(dbUser, department.organisation)
        if (organisationUser.role !== Role.Admin && departmentUser.role !== Role.Admin) {
            throw DgsBadRequestException("You do not have permission to add users")
        }

        var user = userRepository.findByEmail(email)
        if (user == null) {
            user = userRepository.save(UserModel(email = email, password = "", name = "", registered = false)) as UserModel
        }

        departmentUserRepository.save(DepartmentUserModel(department = department, organisation = department.organisation, user = user, role = Role.User, seniority = seniority))

        return Department(id = department.id.toString(), name = department.name, createdBy = department.createdBy.toString())
    }
}