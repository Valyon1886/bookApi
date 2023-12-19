package com.book.api.validator

import com.book.api.entity.User
import com.book.api.service.UserService
import org.springframework.stereotype.Component
import org.springframework.validation.Errors
import org.springframework.validation.ValidationUtils
import org.springframework.validation.Validator


@Component
class UserValidator(private val userService: UserService) : Validator {
    override fun supports(aClass: Class<*>): Boolean {
        return User::class.java == aClass
    }

    override fun validate(o: Any, errors: Errors) {
        val user: User = o as User
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty")
        if (user.name.length < 6 || user.name.length > 32) {
            errors.rejectValue("username", "Size.userForm.username")
        }
        if (userService.findByUsername(user.name) != null) {
            errors.rejectValue("username", "Duplicate.userForm.username")
        }
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty")
        if (user.password.length < 8 || user.password.length > 32) {
            errors.rejectValue("password", "Size.userForm.password")
        }
    }
}