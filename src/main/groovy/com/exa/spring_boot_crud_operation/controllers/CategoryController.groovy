package com.exa.spring_boot_crud_operation.controllers

import com.exa.spring_boot_crud_operation.models.Category
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
@RequestMapping("categories")
class CategoryController {

    @Autowired
    private CategoryService categoryService

    @GetMapping
    def getAllCategories() {
        return categoryService.getAllCategories()
    }

    @GetMapping("/{id}")
    def getCategoryById(@PathVariable("id") Long id) {
        def category = categoryService.getCategoryById(id)

        if (category) {
            return category
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping
    def createCategory(@RequestBody Category category) {
        return categoryService.createCategory(category)
    }

    @PutMapping("/{id}")
    def updateCategory(@PathVariable("id") Long id, @RequestBody Category category) {
        def existingCategory = categoryService.updateCategory(id, category)

        if (existingCategory) {
            return existingCategory
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND)
        }
    }

    @DeleteMapping("/{id}")
    def deleteCategory(@PathVariable("id") Long id) {
        def deletedCategory = categoryService.deleteCategory(id)

        if (deletedCategory) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT)
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND)
        }
    }
}
