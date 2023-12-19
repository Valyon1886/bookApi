package com.book.api.controller

import com.book.api.entity.Book
import com.book.api.entity.User
import com.book.api.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/user")
class UserController (private val userService: UserService) {

    @PostMapping("/registration")
    @ResponseBody
    fun registration(@RequestBody user: User): ResponseEntity<User> {
        if(userService.findByEmail(user.email)!=null) return ResponseEntity.status(HttpStatus.CONFLICT).body(user)
        userService.save(user)
        var temp_user = userService.findByEmail(user.email)!!
        return ResponseEntity.ok(user)
    }

    @PostMapping("/login")
    @ResponseBody
    fun login(@RequestBody user: User): ResponseEntity<User> {
        if(userService.findByEmail(user.email)!=null && userService.auth(user)) return ResponseEntity.ok(user)
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(user)
    }

    @PostMapping("/new")
    @ResponseBody
    fun addUser(@RequestBody user: User): User = userService.addUser(user)

    @PostMapping("/{userId}/blaster/{blasterId}")
    @ResponseBody
    fun addBookToDeskById(@PathVariable userId: Long, @PathVariable deskId: Long, @RequestBody book: Book) = userService.addBookToDeskById(userId, deskId, book)

    @GetMapping("/test")
    @ResponseBody
    fun testos(): String = "kuku"

    @GetMapping("/all")
    @ResponseBody
    fun getUsers(): List<User> = userService.getUsers()

    @DeleteMapping("/del/{id}")
    @ResponseBody
    fun deleteUser(@PathVariable id: Long): String = userService.deleteUser(id)

    @GetMapping("/{id}")
    @ResponseBody
    fun findUser(@PathVariable id: Long): User? = userService.findUser(id)
}