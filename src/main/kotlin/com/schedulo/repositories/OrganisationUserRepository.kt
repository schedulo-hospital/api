package com.schedulo.repositories

import com.schedulo.models.OrganisationModel
import com.schedulo.models.OrganisationUserModel
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface OrganisationUserRepository : MongoRepository<OrganisationUserModel, String> {
    fun findByUserId(userId: ObjectId): List<OrganisationUserModel>
    fun findByUserIdAndOrganisationId(userId: ObjectId, organisationId: ObjectId): OrganisationUserModel
}