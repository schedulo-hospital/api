package com.schedulo.models

import java.time.LocalDateTime
import org.bson.types.ObjectId

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.DocumentReference

import org.optaplanner.core.api.domain.solution.PlanningEntityCollectionProperty
import org.optaplanner.core.api.domain.solution.PlanningScore
import org.optaplanner.core.api.domain.solution.PlanningSolution
import org.optaplanner.core.api.domain.solution.ProblemFactCollectionProperty
import org.optaplanner.core.api.domain.valuerange.ValueRangeProvider
import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore

@PlanningSolution
@Document
data class ScheduleModel (
        @Id
        val id: ObjectId = ObjectId.get(),
        var name: String,
        var start: LocalDateTime,
        var end: LocalDateTime,
        val createdDate: LocalDateTime = LocalDateTime.now(),
        var modifiedDate: LocalDateTime = LocalDateTime.now(),

        @ProblemFactCollectionProperty
        @DocumentReference
        var availablity: List<AvailabilityModel>,

        @ProblemFactCollectionProperty
        @ValueRangeProvider
        @DocumentReference
        var users: List<UserModel>,

        @PlanningEntityCollectionProperty
        @DocumentReference
        var shifts: List<ShiftModel>,

        @PlanningScore
        var score: HardSoftScore
)