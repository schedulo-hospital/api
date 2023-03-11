package com.schedulo.models

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.DBRef

import org.optaplanner.core.api.domain.entity.PlanningEntity
import org.optaplanner.core.api.domain.lookup.PlanningId
import org.optaplanner.core.api.domain.variable.PlanningVariable

import org.bson.types.ObjectId
import java.time.LocalDateTime

@PlanningEntity
@Document
data class ShiftModel (
  @Id
  @PlanningId
  var id:Long,

  var start: LocalDateTime,
  var end: LocalDateTime,

  var location: String,
  var requiredSeniority: String,

  @PlanningVariable
  @DBRef
  var user: UserModel,
)