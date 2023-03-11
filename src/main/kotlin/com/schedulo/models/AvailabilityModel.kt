package com.schedulo.models

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime
import org.optaplanner.core.api.domain.lookup.PlanningId

enum class AvailabilityType {
        DESIRED, UNDESIRED, UNAVAILABLE
}

@Document
data class AvailabilityModel (
        @PlanningId
        @Id
        val id: ObjectId = ObjectId.get(),
        val userId: ObjectId,
        val date: LocalDateTime,
        val type: AvailabilityType,

        val createdDate: LocalDateTime = LocalDateTime.now(),
        val modifiedDate: LocalDateTime = LocalDateTime.now()
)