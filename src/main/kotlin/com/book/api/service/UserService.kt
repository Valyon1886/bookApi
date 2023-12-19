package com.book.api.service

import com.book.api.entity.Book
import com.book.api.entity.DeskOfBook
import com.book.api.entity.User
import com.book.api.error.NoUserException
import com.book.api.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service

@Service
//@Component
class UserService (private val userRepository: UserRepository, private val deskService: DeskService){

//    fun checkIdTokenUser(idToken: String): Boolean {
//        return userRepository.findByIdToken(idToken) != null
//    }
//
    fun findByUsername(username: String): User? {
        return userRepository.findByUsername(username)
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

    fun addBookToDeskById(userId: Long, deskId: Long, book: Book): Book{
        var updateUser = userRepository.findByIdOrNull(userId)
        if(updateUser!=null) {
            var newDesks:MutableList<DeskOfBook>? = updateUser.shelf
            if (newDesks != null) {
                var desk: DeskOfBook = deskService.addBookToDesk(deskId, book)
                deskService.updateDesk(deskId, desk)
                newDesks.add(desk)
            }
            updateUser.shelf = newDesks
            userRepository.save(updateUser)
        } else{
            throw NoUserException("ПОЛЬЗОВАТЕЛЬ С ТАКИМ ID ($userId) НЕ НАЙДЕН");
        }
        return book;
    }

    fun findUser(id: Long): User? = userRepository.findByIdOrNull(id)
}