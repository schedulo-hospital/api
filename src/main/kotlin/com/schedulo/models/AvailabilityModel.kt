package com.schedulo.models

import org.bson.types.ObjectId
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.LocalDate

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.DocumentReference

import org.optaplanner.core.api.domain.lookup.PlanningId

import com.schedulo.generated.types.AvailabilityType

@Document
data class AvailabilityModel (
        @PlanningId
        @Id
        val id: ObjectId = ObjectId.get(),
        @DocumentReference
        val user: UserModel,
        val date: LocalDate,
        var type: AvailabilityType,

        val createdDate: LocalDateTime = LocalDateTime.now(),
        val modifiedDate: LocalDateTime = LocalDateTime.now()
)