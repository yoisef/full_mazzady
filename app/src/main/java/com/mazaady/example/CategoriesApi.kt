package com.mazaady.example

import retrofit2.http.GET

interface CategoriesApi {
    @GET("categories")
    suspend fun getAllCategories(): List<Category>
}