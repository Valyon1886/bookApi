package com.book.api.entity

import java.util.*
import jakarta.persistence.*

@Entity
@Table(name="review")
data class Review(
    @Column(nullable = false) var reviewText: String,
    @Column(nullable = false) var rating: Int,
    @Column(nullable = false) val reviewDate: String,
    @Column(nullable = false) val userId: Long,
    @Column(nullable = false) val userName: String,
    @Id @GeneratedValue val id: Long? = null
)
{
    constructor() : this("test", 1, "123", 1, "")
}
