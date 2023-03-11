package com.schedulo.models

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime
import org.springframework.data.mongodb.core.mapping.DBRef

@Document
data class DepartmentUserModel (
        @Id
        val id: ObjectId = ObjectId.get(),
        @DBRef
        val organisation: OrganisationModel,
        @DBRef
        val department: DepartmentModel,
        @DBRef
        val user: UserModel,
        val role: Role,
        val createdDate: LocalDateTime = LocalDateTime.now(),
        val modifiedDate: LocalDateTime = LocalDateTime.now()
)