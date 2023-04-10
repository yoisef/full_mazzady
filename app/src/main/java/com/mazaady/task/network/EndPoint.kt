package com.mazaady.task.network


import com.mazaady.task.domain.models.CatData
import com.mazaady.task.domain.models.MainResponse
import com.mazaady.task.domain.models.PropData
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface EndPoint {

    @GET("v1/get_all_cats")
   suspend fun getMainCategories( ): MainResponse<CatData>


    @GET("v1/properties")
    suspend fun getProperties(@Query("cat") categoryId : String ): MainResponse<List<PropData>>

    @GET("v1/get-options-child/{id}")
    suspend fun getOptionChild(@Path("id") id :String): MainResponse<List<PropData>>
}