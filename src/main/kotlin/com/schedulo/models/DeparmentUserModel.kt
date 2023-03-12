package com.schedulo.models

import org.bson.types.ObjectId
import java.time.LocalDateTime

import com.schedulo.generated.types.Seniority

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.DocumentReference

@Document
data class DepartmentUserModel (
        @Id
        val id: ObjectId = ObjectId.get(),
        @DocumentReference
        val organisation: OrganisationModel,
        @DocumentReference
        val department: DepartmentModel,
        @DocumentReference
        val user: UserModel,
        val role: Role,
        var seniority: Seniority? = null,
        val createdDate: LocalDateTime = LocalDateTime.now(),
        val modifiedDate: LocalDateTime = LocalDateTime.now()
)