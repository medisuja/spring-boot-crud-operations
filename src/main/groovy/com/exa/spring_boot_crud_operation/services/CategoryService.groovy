package com.exa.spring_boot_crud_operation.services

import com.exa.spring_boot_crud_operation.models.Category
import com.exa.spring_boot_crud_operation.repositories.CategoryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository

    def getAllCategories() {
        return categoryRepository.findAll()
    }

    def getCategoryById(Long id) {
        return categoryRepository.findById(id)
    }

    def createCategory(Category category) {
        return categoryRepository.save(category)
    }

    def updateCategory(Long id, Category category) {
        def existingCategory = categoryRepository.findById(id)

        if (existingCategory.isPresent()) {
            def categoryToUpdate = existingCategory.get()
            categoryToUpdate.name = category.name
            return categoryRepository.save(categoryToUpdate)
        }

        return null
    }

    def deleteCategory(Long id) {
        def existingCategory = categoryRepository.findById(id)

        if (existingCategory.isPresent()) {
            categoryRepository.delete(existingCategory.get())
            return existingCategory.get()
        }

        return null
    }
}