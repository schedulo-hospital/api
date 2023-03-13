package com.schedulo.repositories

import com.schedulo.models.DepartmentUserModel
import com.schedulo.models.UserModel
import com.schedulo.models.OrganisationModel
import com.schedulo.models.DepartmentModel
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Aggregation

interface DepartmentUserRepository : MongoRepository<DepartmentUserModel, String> {
    fun findByUserAndOrganisation(user: ObjectId, organisation: ObjectId): List<DepartmentUserModel>
    fun findByUserAndDepartment(user: ObjectId, department: ObjectId): DepartmentUserModel

    @Aggregation(pipeline = arrayOf(
        "{ \$match: { department: ?0 } }",
        "{ \$lookup: { from: 'userModel', localField: 'user', foreignField: '_id', as: 'userLoaded' }}",
        "{ \$unwind: '\$userLoaded' }",
    ))
    fun findByDepartment(department: ObjectId): List<DepartmentUserModel>
}