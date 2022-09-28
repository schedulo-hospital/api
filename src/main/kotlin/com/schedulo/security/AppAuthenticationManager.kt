package com.schedulo.security

import com.schedulo.repositories.UserRepository
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component
import java.util.*

@Component
class AppAuthenticationManager(private val userRepository: UserRepository
) : AuthenticationManager {
  @Throws(AuthenticationException::class)
  override fun authenticate(authentication: Authentication): Authentication? {
    // val password = authentication.credentials.toString()
    // val user = userRepository.findByEmail(authentication.name)
    // if (!bcr.matches(password, user?.password)) {
    //   throw BadCredentialsException("Bad credentials")
    // }

    return UsernamePasswordAuthenticationToken("username", "password", Arrays.asList())
  }
}