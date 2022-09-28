package com.schedulo.security

import org.springframework.beans.factory.annotation.Value
import com.schedulo.generated.types.User
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.function.Function;

@Component
class JwtTokenUtil : Serializable {
    @Value("\${security.signing-key}")
    private val signingKey: String? = null

    fun getUsernameFromToken(token: String): String {
        return getClaimFromToken(token, Function { it.subject })
    }

    fun getExpirationDateFromToken(token: String): Date {
        return getClaimFromToken(token, Function { it.expiration })
    }

    fun <T> getClaimFromToken(token: String, claimsResolver: Function<Claims, T>): T {
        val claims = getAllClaimsFromToken(token)
        return claimsResolver.apply(claims)
    }

    private fun getAllClaimsFromToken(token: String): Claims {
        return Jwts.parser()
                .setSigningKey(signingKey)
                .parseClaimsJws(token)
                .body
    }

    private fun isTokenExpired(token: String): Boolean {
        val expiration = getExpirationDateFromToken(token)
        return expiration.before(Date())
    }

    fun generateToken(user: User): String {
        return doGenerateToken(user.id)
    }

    private fun doGenerateToken(subject: String): String {

        val claims = Jwts.claims().setSubject(subject)
        claims["scopes"] = Arrays.asList(SimpleGrantedAuthority("ROLE_USER"))

        return Jwts.builder()
                .setClaims(claims)
                .setIssuer("http://schedulo-hospital.com")
                .setIssuedAt(Date(System.currentTimeMillis()))
                .setExpiration(Date(System.currentTimeMillis() + 10 * 365 * 24 * 60 * 60 * 1000))
                .signWith(SignatureAlgorithm.HS256, signingKey)
                .compact()
    }

    fun validateToken(token: String, userDetails: UserDetails): Boolean? {
        val username = getUsernameFromToken(token)
        return username == userDetails.username && !isTokenExpired(token)
    }

    fun getAuthentication(token: String): Authentication? {
      return try {
        val claims = Jwts.parser()
          .setSigningKey(signingKey)
          // .build()
          .parseClaimsJws(token.replace("Bearer ", ""))
        val userId = claims.body.subject
        val scopes = claims.body["scopes"]
          println(scopes)
        val principal = User(userId, "???", "") // TODO: load user and replace real username
        val claims1 = Arrays.asList(SimpleGrantedAuthority("ROLE_USER"))
        // val claims1 = scopes.map{s -> SimpleGrantedAuthority(s)}
        UsernamePasswordAuthenticationToken(principal, token, claims1)
      } catch (e: Exception) {
        return null
      }
    }

}