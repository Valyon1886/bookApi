package com.book.api.entity

import jakarta.persistence.*

@Entity
@Table(name="desk")
data class DeskOfBook(
    @Column(nullable = false) var deskName: String,
    @Column(nullable = false) var numberOfBooks: Int,
    @Column(nullable = false) var location: String,
    @Column(nullable = false) var category: String,
    @OneToMany
    @Column(nullable = true) var books: MutableList<Book>?,
    @Id @GeneratedValue val id: Long? = null
)
{
    constructor() : this("test", 11, "test", "test", mutableListOf())
}
