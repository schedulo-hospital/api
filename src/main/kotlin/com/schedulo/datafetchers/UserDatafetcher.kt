package com.schedulo.datafetchers

import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsMutation
import com.netflix.graphql.dgs.DgsQuery
import com.netflix.graphql.dgs.InputArgument
import com.netflix.graphql.dgs.exceptions.DgsBadRequestException
import com.netflix.graphql.dgs.exceptions.DgsEntityNotFoundException
import com.schedulo.generated.types.LoginInput
import com.schedulo.generated.types.LoginResponse
import com.schedulo.generated.types.RegisterInput
import com.schedulo.generated.types.User
import com.schedulo.models.UserModel
import com.schedulo.repositories.UserRepository
import com.schedulo.security.JwtTokenUtil
import org.springframework.security.access.annotation.Secured
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

class UserAlreadyExistsException(message: String) : DgsBadRequestException(message)

@DgsComponent
class UserDataFetcher(
    val jwtUtil: JwtTokenUtil,
    private val userRepository: UserRepository,
    val bCryptPasswordEncoder: BCryptPasswordEncoder
) {

    @Secured(*arrayOf("ROLE_USER"))
    @DgsQuery
    suspend fun currentUser(): User {
        val user: User = SecurityContextHolder.getContext().getAuthentication().getPrincipal() as User
        val dbUser = userRepository.findById(user.id).get()

        return User(id = dbUser.id.toString(), name = dbUser.name, email = dbUser.email)
    }

    @DgsMutation
    suspend fun login(@InputArgument input : LoginInput): LoginResponse {
        val user = userRepository.findByEmail(input.email) ?: throw DgsEntityNotFoundException("User does not exist")

        if (!bCryptPasswordEncoder.matches(input.password, user.password)) {
            throw DgsBadRequestException("Wrong password")
        }

        val token = jwtUtil.generateToken(User(id = user.id.toString(), name = user.name, email = user.email))
        return LoginResponse(token = token, user = User(id = user.id.toString(), name = user.name, email = user.email))
    }

    @DgsMutation
    suspend fun register(@InputArgument input : RegisterInput): LoginResponse {
        val exists = userRepository.findByEmail(input.email)

        if (exists != null) {
            throw UserAlreadyExistsException("User already exists")
        }

        val user = userRepository.save(UserModel(email = input.email, password = bCryptPasswordEncoder.encode(input.password), name = "bleh"))

        val token = jwtUtil.generateToken(User(id = user.id.toString(), name = user.name, email = user.email))
        return LoginResponse(token = token, user = User(id = user.id.toString(), name = user.name, email = user.email))
    }
}