package com.schedulo.repositories

import com.schedulo.models.AvailabilityModel
import com.schedulo.models.UserModel
import org.springframework.data.mongodb.repository.MongoRepository
import java.time.LocalDate

interface AvailabilityRepository : MongoRepository<AvailabilityModel, String> {
  fun findAllByDateBetweenAndUser(from: LocalDate, to: LocalDate, user: UserModel): List<AvailabilityModel>
  fun findByUserAndDate(user: UserModel, date: LocalDate): AvailabilityModel?
  fun findAllByUserId(user: List<String>): List<AvailabilityModel>
}