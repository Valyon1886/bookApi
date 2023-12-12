package com.book.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BookMavenApplication

fun main(args: Array<String>) {
    runApplication<BookMavenApplication>(*args)
}
