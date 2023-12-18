package com.book.api.service

import com.book.api.entity.Book
import com.book.api.entity.Review
import com.book.api.repository.BookRepository
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import jakarta.persistence.EntityNotFoundException

@Service
//@Component
class BookService(private val bookRepository: BookRepository, private val reviewService: ReviewService) {
    fun createBook(book: Book): Book {
        return bookRepository.save(book)
    }

    fun getAllBook(): List<Book> = bookRepository.findAll()

    fun findBook(id: Long): Book {
        return bookRepository.findById(id).orElseThrow { EntityNotFoundException("Book not found with id: $id") }
    }

    fun add(book: Book): Book{
        return bookRepository.findById(book.id!!).orElseThrow { EntityNotFoundException("Book not found with id") }
    }

    fun updateBook(id: Long, book: Book): Book {
        return bookRepository.findById(id).map {
            it.rate = book.rate
//            it.view = book.view
            it.markReadFlag = book.markReadFlag
//            it.markFinishFlag = book.markFinishFlag
//            it.currentPage = book.currentPage
            it.reviews = book.reviews
            bookRepository.save(it)
        }.orElseThrow { EntityNotFoundException("Book not found with id $id") }
    }

    fun addReviewToBook(id: Long, review: Review): Review{
        bookRepository.findById(id).map {
            var newReviews:MutableList<Review>? = it.reviews
            if (newReviews != null) {
                reviewService.add(review)
                newReviews.add(review)
            }
            it.reviews = newReviews
            it.rate = if (it.reviews!=null) ((it.reviews!!.sumOf { it.rating }) / it.reviews!!.size).toInt() else review.rating
            bookRepository.save(it)
        }.orElseThrow { EntityNotFoundException("Book not found with id $id") }
        return review
    }

    fun getBookByName(name:String): Book{
        return bookRepository.findByName(name)!!  //.orElseThrow { EntityNotFoundException("Book not found with name: $name") }
    }

    fun getBookByAuthor(author:String): Book{
        return bookRepository.findByAuthor(author)!! //.orElseThrow { EntityNotFoundException("Book not found with author: $author") }
    }

    fun startReading(id: Long): Book{
        return bookRepository.findById(id).map {
            it.markReadFlag = true
            bookRepository.save(it)
        }.orElseThrow { EntityNotFoundException("Book not found with id $id") }
    }

    fun endReading(id: Long): Book{
        return bookRepository.findById(id).map {
            it.markReadFlag = false
//            it.markFinishFlag = true
            bookRepository.save(it)
        }.orElseThrow { EntityNotFoundException("Book not found with id $id") }
    }

    fun deleteBook(id: Long): String = bookRepository.deleteById(id).toString()
}