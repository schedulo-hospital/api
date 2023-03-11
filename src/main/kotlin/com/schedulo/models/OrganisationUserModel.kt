package com.schedulo.models

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.DBRef
import java.time.LocalDateTime

enum class Role {
    Admin,
    User
}

@Document
data class OrganisationUserModel (
        @Id
        val id: ObjectId = ObjectId.get(),
        @DBRef
        val organisation: OrganisationModel,
        @DBRef
        val user: UserModel,
        val role: Role,
        val createdDate: LocalDateTime = LocalDateTime.now(),
        val modifiedDate: LocalDateTime = LocalDateTime.now()
)