package com.mazaady

import com.mazaady.task.domain.models.CatData
import com.mazaady.task.domain.models.Category
import com.mazaady.task.domain.models.MainResponse
import com.mazaady.task.domain.models.PropData
import com.mazaady.task.domain.repository.MainRepo
import com.mazaady.task.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow

class FakeMazzadyRepository : MainRepo {

    private val _categories = MutableStateFlow<List<Category>>(arrayListOf())



    override fun getMainCategories(): Flow<MainResponse<CatData>> {
        val categories= mutableListOf<Category>()
        categories.add(Category(null,"o","hello",0,0,"","first","first"))
        categories.add(Category(null,"o","hello",0,1,"","second","second"))


        return flow {
            _categories.emit(categories)

        }

    }


    override fun getProperties(categoryId: String): Flow<MainResponse<List<PropData>>> {

        return flow {  }
    }

    override fun getOptionChild(propertyId: String): Flow<MainResponse<List<PropData>>> {

        return flow {  }

    }
}