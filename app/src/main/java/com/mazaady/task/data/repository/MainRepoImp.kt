package com.mazaady.task.data.repository

import com.mazaady.task.domain.models.CatData
import com.mazaady.task.domain.models.MainResponse
import com.mazaady.task.domain.models.PropData
import com.mazaady.task.domain.repository.MainRepo
import com.mazaady.task.network.EndPoint
import com.mazaady.task.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepoImp @Inject constructor( val endPoint: EndPoint?) : MainRepo {




    override fun getMainCategories(): Flow<MainResponse<CatData>> =

        flow {
            endPoint?.let { emit(it.getMainCategories())
            }
        }

    override fun getProperties(categoryId: String): Flow<MainResponse<List<PropData>>> =
    flow {
        endPoint?.let { emit(it.getProperties(categoryId))
        }
    }

    override fun getOptionChild(propertyId: String): Flow<MainResponse<List<PropData>>> = flow {

        endPoint?.let { emit(it.getOptionChild(propertyId))

        }


    }
}





