package com.book.api.entity

import jakarta.persistence.*
import org.hibernate.annotations.Type

@Entity
@Table(name = "book")
data class Book(
    @Column(nullable = false) val name: String,
    @Column(nullable = false) val author: String,
//    @Column(nullable = false) val productionYear: String,
    @Column(nullable = true) var rate: Int,
    @Lob @Column(nullable = true) val description: String,
    @Column(nullable = true) val image: String,
    @Column(nullable = false) val file: String,
//    @Column(nullable = false) var view: Long,
//    @Column(nullable = false) var markFinishFlag: Boolean,
    @Column(nullable = false) var markReadFlag: Boolean,
//    @Column(nullable = false) var currentPage: Int,
    @Column(nullable = false) val genre: String,
    @OneToMany
    @Column(nullable = true) var reviews: MutableList<Review>?,
    @Id @GeneratedValue var id: Long? = null
) {
    constructor() : this(
        "test",
        "test",
//        "123",
        1,
        "test",
        "test",
        "test",
        false,
        "test",
        mutableListOf()
    )
}
