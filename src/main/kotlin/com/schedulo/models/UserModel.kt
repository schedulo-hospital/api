package com.schedulo.models

import org.bson.types.ObjectId
import java.time.LocalDateTime

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

import org.optaplanner.core.api.domain.lookup.PlanningId

@Document
data class UserModel (
        @Id
        @PlanningId
        val id: ObjectId = ObjectId.get(),
        var name: String,
        val email: String,
        var password: String,
        var registered: Boolean,
        val seniority: String? = null,
        val createdDate: LocalDateTime = LocalDateTime.now(),
        var modifiedDate: LocalDateTime = LocalDateTime.now()
)