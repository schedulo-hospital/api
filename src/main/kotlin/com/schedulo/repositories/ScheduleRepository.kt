package com.schedulo.repositories

import com.schedulo.models.ScheduleModel
import org.springframework.data.mongodb.repository.MongoRepository

interface ScheduleRepository : MongoRepository<ScheduleModel, String> {

}