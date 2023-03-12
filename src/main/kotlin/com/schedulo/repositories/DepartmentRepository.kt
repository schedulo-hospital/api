package com.schedulo.repositories

import com.schedulo.models.DepartmentModel
import com.schedulo.models.OrganisationModel
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface DepartmentRepository : MongoRepository<DepartmentModel, String> {
    fun findAllByOrganisation(organisation: OrganisationModel): List<DepartmentModel>
}