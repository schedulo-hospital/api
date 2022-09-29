package com.schedulo.models

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

enum class Role {
    Admin,
    User
}

@Document
data class OrganisationUserModel (
        @Id
        val id: ObjectId = ObjectId.get(),
        val organisationId: ObjectId,
        val userId: ObjectId,
        val role: Role,
        val createdDate: LocalDateTime = LocalDateTime.now(),
        val modifiedDate: LocalDateTime = LocalDateTime.now()
)