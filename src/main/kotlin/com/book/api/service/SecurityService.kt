package com.book.api.service

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AnonymousAuthenticationToken
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service


@Service
class SecurityService(private val userDetailsService: UserDetailsService) {

    private val authenticationManager: AuthenticationManager? = null

    val isAuthenticated: Boolean
        get() {
            val authentication = SecurityContextHolder.getContext().authentication
            return if (authentication == null || AnonymousAuthenticationToken::class.java.isAssignableFrom(
                    authentication.javaClass
                )
            ) {
                false
            } else authentication.isAuthenticated
        }

    fun autoLogin(username: String?, password: String?) {
        val userDetails: UserDetails = userDetailsService.loadUserByUsername(username)
        val usernamePasswordAuthenticationToken =
            UsernamePasswordAuthenticationToken(userDetails, password, userDetails.authorities)
        authenticationManager!!.authenticate(usernamePasswordAuthenticationToken)
        if (usernamePasswordAuthenticationToken.isAuthenticated) {
            SecurityContextHolder.getContext().authentication = usernamePasswordAuthenticationToken
            logger.debug(String.format("Autologin successful, %s!", username))
        }
    }

    companion object {
        private val logger = LoggerFactory.getLogger(SecurityService::class.java)
    }
}