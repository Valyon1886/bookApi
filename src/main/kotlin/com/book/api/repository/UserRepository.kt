package com.book.api.repository

import com.book.api.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository

interface UserRepository : JpaRepository<User, Long> {
    fun findByIdToken(idToken: String): User?
}