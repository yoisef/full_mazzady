package com.mazaady.example

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {
    @Provides
    fun provideCategoriesRepository(categoriesApi: CategoriesApi): CategoriesRepository {
        return CategoriesRepository(categoriesApi)
    }

    @Provides
    fun provideCategoriesViewModelFactory(categoriesRepository: CategoriesRepository): CategoriesViewModelFactory {
        return CategoriesViewModelFactory(categoriesRepository)
    }
}