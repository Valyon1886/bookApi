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

    @PostMapping("/create")
    @ResponseBody
    fun createBook(@RequestBody book: Book): Book {
        return bookService.createBook(book)
    }

    @PostMapping("/{id}")
    @ResponseBody
    fun findBook(@PathVariable id: Long): Book {
        return bookService.findBook(id)
    }

    @PostMapping("/getAll")
    @ResponseBody
    fun getAllBook(): List<Book> {
        return bookService.getAllBook()
    }

    @PostMapping("/add")
    @ResponseBody
    fun addBook(@RequestBody book: Book): Book {
        return bookService.add(book)
    }

    @PostMapping("update/{id}")
    @ResponseBody
    fun updateBook(@PathVariable id: Long, @RequestBody book: Book): Book {
        return bookService.updateBook(id, book)
    }

    @PostMapping("/addReview/{id}")
    @ResponseBody
    fun addReviewToBook(@PathVariable id: Long, @RequestBody review: Review): Review {
        return bookService.addReviewToBook(id, review)
    }

    @PostMapping("/findByName/{name}")
    @ResponseBody
    fun getBookByName(@PathVariable name: String): Book {
        return bookService.getBookByName(name)
    }

    @PostMapping("/findByAuthor/{author}")
    @ResponseBody
    fun getBookByAuthor(@PathVariable author: String): Book {
        return bookService.getBookByAuthor(author)
    }

    @PostMapping("/{id}/startReading")
    @ResponseBody
    fun startReading(@PathVariable id: Long): Book {
        return bookService.startReading(id)
    }

    @PostMapping("/{id}/endReading")
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
