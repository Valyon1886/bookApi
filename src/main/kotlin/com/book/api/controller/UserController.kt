package com.book.api.controller

import com.book.api.entity.Book
import com.book.api.entity.User
import com.book.api.service.SecurityService
import org.springframework.ui.Model;
import com.book.api.service.UserService
import com.book.api.validator.UserValidator
import org.springframework.stereotype.Controller
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*


@Controller
@RequestMapping("/user")
class UserController (private val userService: UserService, private val securityService: SecurityService,
    private val userValidator: UserValidator) {

//    @GetMapping("/get/token/{idToken}")
//    @ResponseBody
//    fun checkIdTokenUser(@PathVariable idToken: String) = userService.checkIdTokenUser(idToken)

//    @GetMapping("/get/idToken/{idToken}")
//    @ResponseBody
//    fun findUserByIdToken(@PathVariable idToken: String) = userService.findUserByIdToken(idToken)

    @GetMapping("/registration")
    fun registration(model: Model): String {
        if (securityService.isAuthenticated) {
            return "redirect:/"
        }
        model.addAttribute("userForm", User())
        return "registration"
    }

    @PostMapping("/registration")
    @ResponseBody
    fun registration(@RequestBody user: User, bindingResult: BindingResult?, model: Model?): String {
        userValidator.validate(user, bindingResult)
        userService.save(user)
        securityService.autoLogin(user.name, user.password)
        return "redirect:/"
    }

    @GetMapping("/login")
    fun login(model: Model, error: String?, logout: String?): String {
        if (securityService.isAuthenticated()) {
            return "redirect:/"
        }
        if (error != null) model.addAttribute("error", "Ваш логин и/или пароль не верны.")
        if (logout != null) model.addAttribute("message", "Вы успешно вышли из аккаунта.")
        return "login"
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