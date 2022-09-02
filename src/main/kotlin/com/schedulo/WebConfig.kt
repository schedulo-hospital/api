package com.schedulo

import com.schedulo.AppAuthenticationManager
import com.schedulo.JwtTokenUtil
import com.schedulo.JwtTokenFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
// import osahner.security.JWTAuthenticationFilter
// import osahner.security.JWTAuthorizationFilter
// import osahner.security.TokenProvider
// import osahner.service.AppAuthenticationManager

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
class WebConfig(
  // val securityProperties: SecurityProperties,
  val authenticationManager: AppAuthenticationManager,
  val jwtTokenUtil: JwtTokenUtil
) {
  @Bean
  @Throws(Exception::class)
  fun filterChain(http: HttpSecurity): SecurityFilterChain? {
    return http
      .cors().and()
      .csrf().disable()
      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // no sessions
      .and()
      //.authorizeRequests()
      //.antMatchers("/graphql").permitAll()
      //.anyRequest().authenticated()
      //.and()
      // .addFilter(JWTAuthenticationFilter(authenticationManager, securityProperties, tokenProvider))
      // .addFilter(JWTAuthorizationFilter(authenticationManager, securityProperties, tokenProvider))
      .addFilter(JwtTokenFilter(authenticationManager, jwtTokenUtil))
      .build()
  }

  @Bean
  fun corsConfigurationSource(): CorsConfigurationSource = UrlBasedCorsConfigurationSource().also { cors ->
    CorsConfiguration().apply {
      allowedOrigins = listOf("*")
      allowedMethods = listOf("POST", "PUT", "DELETE", "GET", "OPTIONS", "HEAD")
      allowedHeaders = listOf(
        "Authorization",
        "Content-Type",
        "X-Requested-With",
        "Accept",
        "Origin",
        "Access-Control-Request-Method",
        "Access-Control-Request-Headers"
      )
      exposedHeaders = listOf(
        "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials", "Authorization", "Content-Disposition"
      )
      maxAge = 3600
      cors.registerCorsConfiguration("/**", this)
    }
  }
}