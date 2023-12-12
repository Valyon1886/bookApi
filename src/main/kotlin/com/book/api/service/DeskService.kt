package com.book.api.service

import com.book.api.entity.Book
import com.book.api.entity.DeskOfBook
import com.book.api.repository.BookRepository
import com.book.api.repository.DeskOfBookRepository
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import jakarta.persistence.EntityNotFoundException

@Service
//@Component
class DeskService(private val deskRepository: DeskOfBookRepository, private val bookRepository: BookRepository,
                  private val bookService: BookService) {

    fun add(desk: DeskOfBook): DeskOfBook{
        return deskRepository.save(desk)
    }

    fun updateDesk(id: Long, desk: DeskOfBook): DeskOfBook{
        return deskRepository.findById(id).map {
            it.location = desk.location
            it.numberOfBooks = desk.numberOfBooks
            it.category = desk.category
            it.deskName = desk.deskName
            it.books = desk.books
            deskRepository.save(it)
        }.orElseThrow { EntityNotFoundException("Desk not found with id $id") }
    }

    fun addBookToDesk(id: Long, book: Book): DeskOfBook {
        return deskRepository.findById(id).map {
            var newBooks:MutableList<Book>? = it.books
            if (newBooks != null) {
                bookService.add(book)
                newBooks.add(book)
            }
            it.books = newBooks

            deskRepository.save(it)
        }.orElseThrow { EntityNotFoundException("Book not found with id $id") }
    }

    fun deleteDesk(id: Long): String = deskRepository.deleteById(id).toString()
}