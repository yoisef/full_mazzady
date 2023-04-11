package com.mazaady.example


import kotlinx.coroutines.runBlocking

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import com.nhaarman.mockitokotlin2.whenever
import org.mockito.junit.MockitoJUnitRunner

import java.util.*
@RunWith(MockitoJUnitRunner::class)
class CategoriesViewModelTest {
    @Mock
    private lateinit var categoriesRepository: CategoriesRepository
    private lateinit var categoriesViewModel: CategoriesViewModel

    @Before
    fun setup() {
        categoriesViewModel = CategoriesViewModel(categoriesRepository)
    }

    @Test
    fun `test loadCategories with success`() = runBlocking {
        // Set up mock category data
        val mockCategories = listOf(
            Category(1, "Category 1"),
            Category(2, "Category 2"),
            Category(3, "Category 3")
        )

        // Set up mock repository response
        whenever(categoriesRepository.getAllCategories()).thenReturn(mockCategories)

        // Trigger loadCategories
        categoriesViewModel.loadCategories()

        // Verify viewModel state
        assertEquals(mockCategories, categoriesViewModel.categories.value)
        assertEquals(false, categoriesViewModel.loading.value)
        assertNull(categoriesViewModel.error.value)
    }

    @Test
    fun `test loadCategories with error`() = runBlocking {
        // Set up mock error
        val mockError = Exception("Error fetching categories")

        // Set up mock repository response
        whenever(categoriesRepository.getAllCategories()).thenThrow(mockError)

        // Trigger loadCategories
        categoriesViewModel.loadCategories()

        // Verify viewModel state
        assertTrue(categoriesViewModel.categories.value.isEmpty())
        assertEquals(false, categoriesViewModel.loading.value)
        assertEquals(mockError, categoriesViewModel.error.value)
    }
}