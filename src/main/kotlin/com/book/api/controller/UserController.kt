package com.book.api.controller

import com.book.api.entity.Book
import com.book.api.entity.User
import com.book.api.service.UserService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/user")
class UserController (private val userService: UserService) {

    @GetMapping("/get/token/{idToken}")
    @ResponseBody
    fun checkIdTokenUser(@PathVariable idToken: String) = userService.checkIdTokenUser(idToken)

    @GetMapping("/get/idToken/{idToken}")
    @ResponseBody
    fun findUserByIdToken(@PathVariable idToken: String) = userService.findUserByIdToken(idToken)

    @PostMapping("/new")
    @ResponseBody
    fun addUser(@RequestBody user: User): User = userService.addUser(user)

    @PostMapping("/{userId}/blaster/{blasterId}")
    @ResponseBody
    fun addBookToDeskById(@PathVariable userId: Int, @PathVariable deskId: Long, @RequestBody book: Book) = userService.addBookToDeskById(userId, deskId, book)

    @GetMapping("/test")
    @ResponseBody
    fun testos(): String = "kuku"

    @GetMapping("/all")
    @ResponseBody
    fun getUsers(): List<User> = userService.getUsers()

    @DeleteMapping("/del/{id}")
    @ResponseBody
    fun deleteUser(@PathVariable id: Int): String = userService.deleteUser(id)

    @GetMapping("/{id}")
    @ResponseBody
    fun findUser(@PathVariable id: Int): User? = userService.findUser(id)
}