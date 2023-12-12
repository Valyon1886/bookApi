package com.book.api.repository

import com.book.api.entity.DeskOfBook
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository

interface DeskOfBookRepository : JpaRepository<DeskOfBook, Long> {

}