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
class UserService (private val userRepository: UserRepository, private val deskService: DeskService){

    fun findByName(name: String): User? {
        return userRepository.findByName(name)
    }


    fun save(user: User) {
        // Шифрование пароля в SHA-256
        user.password = DigestUtils.sha256Hex(user.password)
        val desk = deskService.add(DeskOfBook(null))
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