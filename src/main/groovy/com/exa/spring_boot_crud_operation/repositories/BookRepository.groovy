package com.exa.spring_boot_crud_operation.repositories

import com.exa.spring_boot_crud_operation.models.Book
import org.springframework.data.jpa.repository.JpaRepository

interface BookRepository extends JpaRepository<Book, Long> {
}
