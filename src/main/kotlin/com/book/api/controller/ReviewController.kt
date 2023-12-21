package com.book.api.controller

import com.book.api.entity.Review
import com.book.api.service.ReviewService
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/review")
class ReviewController(private val reviewService: ReviewService) {

    @PostMapping("/add")
    @ResponseBody
    fun addReview(@RequestBody review: Review): Review {
        return reviewService.add(review)
    }

    @PostMapping("/get/{id}")
    @ResponseBody
    fun getReviewList(@PathVariable id: Long): List<Review> {
        return reviewService.getAllByIdBook(id)
    }

    @PostMapping("/{id}")
    fun deleteReview(@PathVariable id: Long): ResponseEntity<String> {
        reviewService.delete(id)
        return ResponseEntity.ok("Review with id: $id has been deleted")
    }
}