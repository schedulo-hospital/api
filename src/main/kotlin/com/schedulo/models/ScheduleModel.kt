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
class ScheduleModel {
        @Id
        val id: ObjectId = ObjectId.get()
        var name: String? = null
        var start: LocalDateTime? = null
        var end: LocalDateTime? = null
        val createdDate: LocalDateTime = LocalDateTime.now()
        var modifiedDate: LocalDateTime = LocalDateTime.now()

        @DocumentReference
        var department: DepartmentModel? = null

        @ProblemFactCollectionProperty
        var availablity: List<AvailabilityModel>? = null

        @ProblemFactCollectionProperty
        @ValueRangeProvider
        var users: List<UserModel>? = null

        @PlanningEntityCollectionProperty
        var shifts: List<ShiftModel>? = null

        @PlanningScore
        var score: HardSoftScore? = null

        constructor()

        override fun toString(): String = "ScheduleModel($id)"
}