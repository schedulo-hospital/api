package com.schedulo.repositories

import com.schedulo.models.ShiftModel
import org.springframework.data.mongodb.repository.MongoRepository

interface ShiftRepository : MongoRepository<ShiftModel, String> {

}