package com.schedulo.repositories

import com.schedulo.models.ScheduleModel
import com.schedulo.models.DepartmentModel
import org.springframework.data.mongodb.repository.MongoRepository

interface ScheduleRepository : MongoRepository<ScheduleModel, String> {
  fun findByDepartment(department: DepartmentModel): List<ScheduleModel>
}