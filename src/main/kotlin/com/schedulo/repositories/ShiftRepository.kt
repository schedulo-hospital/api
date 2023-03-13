package com.schedulo.repositories

import com.schedulo.models.ShiftModel
import com.schedulo.models.ScheduleModel
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Aggregation
import org.bson.types.ObjectId

interface ShiftRepository : MongoRepository<ShiftModel, String> {
  @Aggregation(pipeline = arrayOf(
    "{ \$match: { schedule: ?0 } }",
    "{ \$lookup: { pipeline: [ { \$lookup: { from: 'userModel', localField: 'user', foreignField: '_id', as: 'userLoaded' } }, { \$unwind: '\$userLoaded' }], from: 'departmentUserModel', localField: 'user', foreignField: '_id', as: 'userLoaded' }}",
    "{ \$unwind: { path: '\$userLoaded', preserveNullAndEmptyArrays: true } }",
  ))
  fun findAllBySchedule(schedule: ObjectId): List<ShiftModel>
}