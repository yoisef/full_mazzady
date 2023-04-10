package com.mazaady.task.domain.repository

import com.mazaady.task.domain.models.CatData
import com.mazaady.task.domain.models.MainResponse
import com.mazaady.task.domain.models.PropData
import kotlinx.coroutines.flow.Flow

interface MainRepo {

    fun getMainCategories(): Flow<MainResponse<CatData>>


    fun getProperties(categoryId :String): Flow<MainResponse<List<PropData>>>


    fun getOptionChild(propertyId : String): Flow<MainResponse<List<PropData>>>

}