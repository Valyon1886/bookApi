package com.book.api.service

import com.book.api.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class UserDetailsService(private val userRepository: UserRepository) {
    @Transactional(readOnly = true)
    @Throws(UsernameNotFoundException::class)
    fun loadUserByUsername(username: String?): UserDetails {
        val user = userRepository.findByUsername(username) ?: throw UsernameNotFoundException(username)
        val grantedAuthorities: Set<GrantedAuthority> = HashSet<GrantedAuthority>()
        return org.springframework.security.core.userdetails.User(user.name, user.password, grantedAuthorities)
    }
}