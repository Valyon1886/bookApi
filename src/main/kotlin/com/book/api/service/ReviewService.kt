package com.book.api.service

import com.book.api.entity.Review
import com.book.api.repository.ReviewRepository
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service

@Service
//@Component
class ReviewService(private val reviewRepository: ReviewRepository) {
    fun add(review: Review): Review {
        return reviewRepository.save(review)
    }

    fun delete(id: Long): String {
        reviewRepository.deleteById(id)
        return "OK"//Response.isOk();  //  TODO Сделать через json
    }

}