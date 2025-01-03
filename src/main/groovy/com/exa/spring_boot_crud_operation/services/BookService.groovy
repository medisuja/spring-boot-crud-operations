package com.exa.spring_boot_crud_operation.services

import com.exa.spring_boot_crud_operation.models.Book
import com.exa.spring_boot_crud_operation.models.Category
import com.exa.spring_boot_crud_operation.repositories.BookRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class BookService {

    @Autowired
    private BookRepository bookRepository

    def getAllBooks() {
        return bookRepository.findAll()
    }

    def getBookById(Long id) {
        return bookRepository.findById(id)
    }

    def createBook(Book book) {
        return bookRepository.save(book)
    }

    def updateBook(Long id, Map<String, Object> bookPayload, Category category) {
        def existingBook = bookRepository.findById(id)

        if (existingBook.isPresent()) {
            def bookToUpdate = existingBook.get()
            bookToUpdate.name = bookPayload.name
            bookToUpdate.isbn = bookPayload.isbn
            bookToUpdate.category = category

            return bookRepository.save(bookToUpdate)
        }

        return null
    }

    def deleteBook(Long id) {
        def existingBook = bookRepository.findById(id)

        if (existingBook.isPresent()) {
            bookRepository.delete(existingBook.get())
            return existingBook.get()
        }

        return null
    }
}
