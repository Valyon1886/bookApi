package com.book.api.entity

import java.util.*
import jakarta.persistence.*

@Entity
@Table(name="review")
data class Review(
    @Column(nullable = false) var reviewText: String,
    @Column(nullable = false) var rating: Double,
    @Column(nullable = false) val reviewDate: String,
    @Id @GeneratedValue val id: Long? = null
)
{
    constructor() : this("test", 11.2, "123")
}
