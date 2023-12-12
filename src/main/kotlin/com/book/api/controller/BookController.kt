package com.book.api.controller

import com.book.api.entity.Book
import com.book.api.entity.Review
import com.book.api.service.BookService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

////@Component
@Controller
@RequestMapping("/book")
class BookController(private val bookService: BookService) {

    @GetMapping("/create")
    @ResponseBody
    fun createBook(@RequestBody book: Book): Book {
        return bookService.createBook(book)
    }

    @GetMapping("/{id}")
    @ResponseBody
    fun findBook(@PathVariable id: Long): Book {
        return bookService.findBook(id)
    }

    @GetMapping("/add")
    @ResponseBody
    fun addBook(@RequestBody book: Book): Book {
        return bookService.add(book)
    }

    @GetMapping("update/{id}")
    @ResponseBody
    fun updateBook(@PathVariable id: Long, @RequestBody book: Book): Book {
        return bookService.updateBook(id, book)
    }

    @GetMapping("/{id}/addReview")
    @ResponseBody
    fun addReviewToBook(@PathVariable id: Long, @RequestBody review: Review): Review {
        return bookService.addReviewToBook(id, review)
    }

    @GetMapping("/findByName/{name}")
    @ResponseBody
    fun getBookByName(@PathVariable name: String): Book {
        return bookService.getBookByName(name)
    }

    @GetMapping("/findByAuthor/{author}")
    @ResponseBody
    fun getBookByAuthor(@PathVariable author: String): Book {
        return bookService.getBookByAuthor(author)
    }

    @GetMapping("/{id}/startReading")
    @ResponseBody
    fun startReading(@PathVariable id: Long): Book {
        return bookService.startReading(id)
    }

    @GetMapping("/{id}/endReading")
    @ResponseBody
    fun endReading(@PathVariable id: Long): Book {
        return bookService.endReading(id)
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    fun deleteBook(@PathVariable id: Long): String {
        return bookService.deleteBook(id)
    }
}
