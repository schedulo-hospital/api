package com.schedulo.models

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.DocumentReference
import java.time.LocalDateTime

@Document
data class DepartmentModel (
        @Id
        val id: ObjectId = ObjectId.get(),
        val name: String,
        @DocumentReference
        val organisation: OrganisationModel,
        val createdBy: ObjectId,
        val createdDate: LocalDateTime = LocalDateTime.now(),
        val modifiedDate: LocalDateTime = LocalDateTime.now()
)