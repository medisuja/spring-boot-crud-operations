package com.exa.spring_boot_crud_operation.repositories

import com.exa.spring_boot_crud_operation.models.Category
import org.springframework.data.jpa.repository.JpaRepository

interface CategoryRepository extends JpaRepository<Category, Long> {
}