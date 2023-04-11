package com.mazaady.example

import javax.inject.Inject

class CategoriesRepository  @Inject constructor(private val categoriesApi: CategoriesApi) {
    suspend fun getAllCategories(): List<Category> {
        return categoriesApi.getAllCategories()
    }
}