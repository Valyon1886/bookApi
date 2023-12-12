package com.book.api.repository

import com.book.api.entity.Book
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository

interface BookRepository : JpaRepository<Book, Long> {
    fun findByName(name: String): Book?
    fun findByAuthor(author: String): Book?

}