package com.book.api.entity

import java.util.*

import jakarta.persistence.*

@Entity
@Table(name="_user")
data class User(
//    @Column(nullable = true) var idToken: String?,
    @Column(nullable = false) val name: String,
    @Column(nullable = false) val registerDate: String,
    @Column(nullable = false) var password: String,
    @OneToMany
    @Column(nullable = true) var shelf: MutableList<DeskOfBook>?,
    @Id @GeneratedValue val id: Long? = null
)
{
    constructor() : this(
//        null,
        "test", "2012-07-16", "123", mutableListOf())
}
