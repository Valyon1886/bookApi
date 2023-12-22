package com.book.api.service

import com.book.api.entity.Book
import com.book.api.entity.DeskOfBook
import com.book.api.entity.User
import com.book.api.error.NoUserException
import com.book.api.repository.UserRepository
import org.apache.commons.codec.digest.DigestUtils
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service

@Service
//@Component
class UserService (private val userRepository: UserRepository, private val deskService: DeskService,
                   private val bookService: BookService){

    fun findByName(name: String): User? {
        return userRepository.findByName(name)
    }


    fun save(user: User) {
        // Шифрование пароля в SHA-256
        user.password = DigestUtils.sha256Hex(user.password)
        val desk = deskService.add(DeskOfBook(mutableListOf()))
        var temp_desk =
        user.shelf?.add(desk)
        userRepository.save(user)
    }

    fun auth(user: User): Boolean {
        val temp_user = userRepository.findByName(user.name)
        if(user.name.equals(temp_user!!.name) &&
            DigestUtils.sha256Hex(user.password).equals(temp_user!!.password))
            return true
        return false
    }

    fun addUser(user: User): User {
        for (i in user.shelf!!) {
            deskService.add(i)
        }
        return userRepository.save(user)
    }

    fun getUsers(): List<User> = userRepository.findAll().toList()

    fun deleteUser(id: Long): String {
        userRepository.deleteById(id)
        return "OK"//Response.isOk();  //  TODO Сделать через json
    }

    fun addBookToDeskById(userId: Long, bookId: Long): User{
        var updateUser = userRepository.findByIdOrNull(userId)
        var book = bookService.findBook(bookId)
        var desks = updateUser?.shelf
        println(updateUser)
        println(book)
        println(desks)
        if(updateUser!=null) {
            desks?.forEach { desk ->
                desk.books?.add(book)
            }
            println(desks)
            updateUser.shelf = desks
            userRepository.save(updateUser)
        } else{
            throw NoUserException("ПОЛЬЗОВАТЕЛЬ С ТАКИМ ID ($userId) НЕ НАЙДЕН");
        }
        return updateUser;
    }

    fun findUser(id: Long): User? = userRepository.findByIdOrNull(id)
}