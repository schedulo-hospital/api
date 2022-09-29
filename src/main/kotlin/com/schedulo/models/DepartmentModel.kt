package com.schedulo.models

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document
data class DepartmentModel (
        @Id
        val id: ObjectId = ObjectId.get(),
        val name: String,
        val organisationId: ObjectId,
        val createdBy: ObjectId,
        val createdDate: LocalDateTime = LocalDateTime.now(),
        val modifiedDate: LocalDateTime = LocalDateTime.now()
)