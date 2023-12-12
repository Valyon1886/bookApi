package com.book.api.repository

import com.book.api.entity.Review
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository

interface ReviewRepository : JpaRepository<Review, Long> {

}