package com.schedulo.security

import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component
import java.util.Arrays;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Component
class AppAuthenticationManager(
  // val bCryptPasswordEncoder: BCryptPasswordEncoder, // private val userService: AppUserDetailsService, 
) : AuthenticationManager {
  @Throws(AuthenticationException::class)
  override fun authenticate(authentication: Authentication): Authentication? {
    // val password = authentication.credentials.toString()
    // val user = userService.loadUserByUsername(authentication.name)
    // if (!bCryptPasswordEncoder.matches(password, "test")) {
    //   throw BadCredentialsException("Bad credentials")
    // }

    return UsernamePasswordAuthenticationToken("username", "password", Arrays.asList())
  }
}