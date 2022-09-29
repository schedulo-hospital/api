package com.schedulo.repositories

import com.schedulo.models.DepartmentUserModel
import com.schedulo.models.OrganisationUserModel
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface DepartmentUserRepository : MongoRepository<DepartmentUserModel, String> {
    fun findByUserIdAndOrganisationId(userId: ObjectId, organisationId: ObjectId): List<DepartmentUserModel>
    fun findByUserIdAndDepartmentId(userId: ObjectId, departmentId: ObjectId): DepartmentUserModel
}