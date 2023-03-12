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
        var id: ObjectId = ObjectId.get(),
        var name: String = "",
        var email: String = "",
        var password: String? = null,
        var registered: Boolean = false,
        val createdDate: LocalDateTime = LocalDateTime.now(),
        var modifiedDate: LocalDateTime = LocalDateTime.now()
) {

}