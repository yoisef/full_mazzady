package com.mazaady.example

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CategoriesViewModelFactory(private val categoriesRepository: CategoriesRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CategoriesViewModel::class.java)) {
            return CategoriesViewModel(categoriesRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}