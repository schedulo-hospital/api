package com.schedulo.repositories

import com.schedulo.models.OrganisationModel
import com.schedulo.models.OrganisationUserModel
import com.schedulo.models.UserModel
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface OrganisationUserRepository : MongoRepository<OrganisationUserModel, String> {
    fun findByUserId(user: UserModel): List<OrganisationUserModel>
    fun findByUserIdAndOrganisationId(user: UserModel, organisation: OrganisationModel): OrganisationUserModel
}