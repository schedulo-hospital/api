package com.schedulo.models

import java.time.LocalDateTime
import java.time.LocalDate
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
        var id: ObjectId = ObjectId.get()
        var name: String? = null
        var start: LocalDate? = null
        var end: LocalDate? = null
        var createdDate: LocalDateTime = LocalDateTime.now()
        var modifiedDate: LocalDateTime = LocalDateTime.now()

        @DocumentReference
        var department: DepartmentModel? = null

        @ProblemFactCollectionProperty
        var availablity: List<AvailabilityModel>? = null

        @ProblemFactCollectionProperty
        @ValueRangeProvider
        var users: List<DepartmentUserModel>? = null

        @PlanningEntityCollectionProperty
        var shifts: List<ShiftModel>? = null

        @PlanningScore
        var score: HardSoftScore? = null

        var solverScore: String = ""

        constructor()

        constructor (name: String, start: LocalDate, end: LocalDate, department: DepartmentModel) {
                this.name = name
                this.start = start
                this.end = end
                this.department = department
        }

        override fun toString(): String = "ScheduleModel($id)"
}