package com.schedulo.datafetchers

import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsMutation
import com.netflix.graphql.dgs.DgsQuery
import com.netflix.graphql.dgs.InputArgument
import com.schedulo.JwtTokenUtil
import com.schedulo.generated.types.LoginInput
import com.schedulo.generated.types.LoginResponse
import com.schedulo.generated.types.User
import org.springframework.security.access.annotation.Secured
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;

@DgsComponent
class UserDataFetcher(val jwtUtil: JwtTokenUtil) {

    @Secured("ROLE_USER")
    @DgsQuery
    suspend fun currentUser(): User {
        val user: User = SecurityContextHolder.getContext().getAuthentication().getPrincipal() as User
        // TODO: get user from db by id - user.id

        return user
    }

    @DgsMutation
    suspend fun login(@InputArgument input : LoginInput): LoginResponse {
        // TODO: get user from db, check password, generate token
        val token = jwtUtil.generateToken(User(id = "1", name = "Mocked user"))
        return LoginResponse(token = token, user = User(id = "1", name = "Mocked user"))
    }
}