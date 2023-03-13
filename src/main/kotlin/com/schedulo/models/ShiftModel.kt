package com.schedulo.models

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.DocumentReference

import org.optaplanner.core.api.domain.entity.PlanningEntity
import org.optaplanner.core.api.domain.lookup.PlanningId
import org.optaplanner.core.api.domain.variable.PlanningVariable

import org.bson.types.ObjectId
import java.time.LocalDateTime

@PlanningEntity
@Document
class ShiftModel {
  @Id
  @PlanningId
  var id: ObjectId = ObjectId.get()

  var start: LocalDateTime? = null
  var end: LocalDateTime? = null

  var requiredSeniority: String? = null

  var schedule: ObjectId? = null

  // @DocumentReference
  var user: ObjectId? = null

  @PlanningVariable
  var userLoaded: DepartmentUserModel? = null

  constructor()

  constructor(start: LocalDateTime?, end: LocalDateTime?, requiredSeniority: String?, schedule: ObjectId?) {
    this.start = start
    this.end = end
    this.requiredSeniority = requiredSeniority
    this.schedule = schedule
  }

  override fun toString(): String = "ShiftModel($id)"
}