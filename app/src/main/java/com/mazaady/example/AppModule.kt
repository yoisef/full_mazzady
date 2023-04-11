package com.mazaady.example

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideCategoriesApi(): CategoriesApi {
        return Retrofit.Builder()
            .baseUrl("https://your-api-base-url.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CategoriesApi::class.java)
    }
}