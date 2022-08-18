package com.schedulo.datafetchers

import com.schedulo.generated.types.Organization
import com.schedulo.generated.types.OrganizationInput
import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsQuery
import com.netflix.graphql.dgs.DgsMutation
import com.netflix.graphql.dgs.InputArgument
import graphql.relay.Connection
import graphql.relay.SimpleListConnection
import graphql.schema.DataFetchingEnvironment

@DgsComponent
class OrganizationDataFetcher() {

    @DgsQuery
    suspend fun organizations(env: DataFetchingEnvironment): Connection<Organization> {
        return SimpleListConnection(listOf(
            Organization(id = "222", name = "Stranger Things", createdBy = "Someone"),
            Organization(id = "1", name = "Stranger Things", createdBy = "Someone")
        )).get(env)
    }

    @DgsQuery
    suspend fun organization(@InputArgument id : String): Organization {
        return Organization(id = id, name = "Stranger Things", createdBy = "Someone")
    }

    @DgsMutation
    suspend fun createOrganization(@InputArgument input : OrganizationInput): Organization {
        System.out.println("Creating organization: " + input.name)
        return Organization(id = "", name = "Stranger Things", createdBy = "Someone")
    }
}