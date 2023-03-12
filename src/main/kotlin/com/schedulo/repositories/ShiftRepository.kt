package com.schedulo.repositories

import com.schedulo.models.ShiftModel
import com.schedulo.models.ScheduleModel
import org.springframework.data.mongodb.repository.MongoRepository

interface ShiftRepository : MongoRepository<ShiftModel, String> {
  fun findAllBySchedule(scheduleId: ScheduleModel): List<ShiftModel>
}