package com.schedulo.repositories

import com.schedulo.models.OrganisationModel
import org.springframework.data.mongodb.repository.MongoRepository

interface OrganisationRepository : MongoRepository<OrganisationModel, String> {

}