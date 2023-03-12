package com.schedulo.repositories

import com.schedulo.models.DepartmentUserModel
import com.schedulo.models.UserModel
import com.schedulo.models.OrganisationModel
import com.schedulo.models.DepartmentModel
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface DepartmentUserRepository : MongoRepository<DepartmentUserModel, String> {
    fun findByUserIdAndOrganisationId(user: UserModel, organisation: OrganisationModel): List<DepartmentUserModel>
    fun findByUserIdAndDepartmentId(user: UserModel, department: DepartmentModel): DepartmentUserModel
    fun findByDepartment(department: DepartmentModel): List<DepartmentUserModel>
}