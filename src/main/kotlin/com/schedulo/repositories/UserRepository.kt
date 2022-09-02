package com.schedulo.repositories

import com.schedulo.models.UserModel
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query

interface UserRepository : MongoRepository<UserModel, String> {
    @Query("{email:'?0'}")
    fun findByEmail(email: String): UserModel?
}