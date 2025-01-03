package com.exa.spring_boot_crud_operation.controllers

import com.exa.spring_boot_crud_operation.models.Book
import com.exa.spring_boot_crud_operation.services.BookService
import com.exa.spring_boot_crud_operation.services.CategoryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("books")
class BookController {

    @Autowired
    private BookService bookService

    @Autowired
    private CategoryService categoryService

    @GetMapping
    def getAllBooks() {
        return bookService.getAllBooks()
    }

    @GetMapping("/{id}")
    def getBookById(@PathVariable("id") Long id) {
        def book = bookService.getBookById(id)

        if (book) {
            return book
        } else {
            def response = [message: "Data not found"]
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping
    def createBook(@RequestBody Map<String, Object> payload) {

        Long categoryId = payload.category as Long
        def category = categoryService.getCategoryById(categoryId)

        if (!category.isPresent()) {
            def response = [message: "Category not found"]
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND)
        }

        def book = new Book(
                category: category.get(),
                name: payload.name,
                isbn: payload.isbn
        )

        return bookService.createBook(book)
    }

    @PutMapping("/{id}")
    def updateBook(@PathVariable("id") Long id, @RequestBody Map<String, Object> payload) {
        Long categoryId = payload.category as Long
        def category = categoryService.getCategoryById(categoryId)

        if (!category.isPresent()) {
            def response = [message: "Category not found"]
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND)
        }

        def updatedBook = bookService.updateBook(id, payload, category.get())

        if (updatedBook == null) {
            def response = [message: "Book not found"]
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND)
        }

        return new ResponseEntity<>(updatedBook, HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    def deleteBook(@PathVariable("id") Long id) {
        def deletedBook = bookService.deleteBook(id)

        if (deletedBook) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT)
        } else {
            def response = [message: "Book not found"]
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND)
        }
    }
}
