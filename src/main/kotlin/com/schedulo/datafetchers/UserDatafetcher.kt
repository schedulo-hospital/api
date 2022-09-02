package com.schedulo.datafetchers

import com.schedulo.generated.types.User
import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsQuery
import com.netflix.graphql.dgs.DgsMutation
import com.netflix.graphql.dgs.InputArgument
import graphql.relay.Connection
import graphql.relay.SimpleListConnection
import graphql.schema.DataFetchingEnvironment

@DgsComponent
class UserDataFetcher() {
    @DgsQuery
    suspend fun currentUser(): User {
        return User(id = "1", name = "Mocked user")
    }
}