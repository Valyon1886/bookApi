package com.book.api.controller

import com.book.api.entity.Book
import com.book.api.entity.DeskOfBook
import com.book.api.service.DeskService
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/desk")
class DeskController(private val deskService: DeskService) {

    @GetMapping("/add")
    @ResponseBody
    fun addDesk(@RequestBody desk: DeskOfBook): DeskOfBook {
        return deskService.add(desk)
    }

    @GetMapping("/{id}")
    @ResponseBody
    fun updateDesk(@PathVariable id: Long, @RequestBody desk: DeskOfBook): DeskOfBook {
        return deskService.updateDesk(id, desk)
    }

    @GetMapping("/addBook/{id}")
    @ResponseBody
    fun addBookToDesk(@PathVariable id: Long, @RequestBody book: Book): DeskOfBook {
        return deskService.addBookToDesk(id, book)
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    fun deleteDesk(@PathVariable id: Long): ResponseEntity<String> {
        deskService.deleteDesk(id)
        return ResponseEntity.ok("Desk with id: $id has been deleted")
    }
}