package com.schedulo.security

import com.schedulo.generated.types.User
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import java.io.IOException
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import jakarta.servlet.FilterChain

class JwtTokenFilter(
  authManager: AuthenticationManager,
  private val jwtTokenUtil: JwtTokenUtil
) : BasicAuthenticationFilter(authManager) {

    @Throws(IOException::class, ServletException::class)
    override fun doFilterInternal(
            req: HttpServletRequest,
            res: HttpServletResponse,
            chain: FilterChain
    ) {
        val header = req.getHeader("Authorization")
        if (header == null || !header.startsWith("Bearer ")) {
            chain.doFilter(req, res)
            return
        }

        jwtTokenUtil.getAuthentication(header)?.also { authentication ->
            SecurityContextHolder.getContext().authentication = authentication
        }
        chain.doFilter(req, res)
    }
}
