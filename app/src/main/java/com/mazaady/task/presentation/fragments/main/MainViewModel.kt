package com.mazaady.task.presentation.fragments.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mazaady.task.domain.models.CatData
import com.mazaady.task.domain.models.MainResponse
import com.mazaady.task.domain.models.Option
import com.mazaady.task.domain.models.PropData
import com.mazaady.task.domain.repository.MainRepo
import com.mazaady.task.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val mainRepo: MainRepo) : ViewModel() {


    private val _getMainCategories: MutableSharedFlow<Resource<MainResponse<CatData>>> = MutableSharedFlow(replay = 0, extraBufferCapacity = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    val getMainCategories :SharedFlow<Resource<MainResponse<CatData>>> =_getMainCategories

    private val _getProperties: MutableStateFlow<Resource<MainResponse<List<PropData>>>> = MutableStateFlow(Resource.error(null,""))
    val getProperties :StateFlow<Resource<MainResponse<List<PropData>>>> =_getProperties

    private val _getOptionsChild: MutableSharedFlow<Resource<MainResponse<List<PropData>>>> = MutableSharedFlow(replay = 0, extraBufferCapacity = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    val getOptionsChild :SharedFlow<Resource<MainResponse<List<PropData>>>> =_getOptionsChild

    fun getMainCategories()
    {
        viewModelScope.launch {
            _getMainCategories.emit(Resource.loading(null))
            mainRepo.getMainCategories().collect{

                if (it.data.categories.isNotEmpty())
                {
                    _getMainCategories.emit(Resource.success(it))
                }else{
                    _getMainCategories.emit(Resource.error(it,"Categories Is Empty"))

                }
            }
        }

    }

    fun getProperties(categoryId : String)
    {
        viewModelScope.launch {

            _getProperties.emit(Resource.loading(null))
            mainRepo.getProperties(categoryId).collect{
                if (it.data.isNotEmpty())
                {
                    _getProperties.emit(Resource.success(it))
                }else{
                    _getProperties.emit(Resource.error(it,"Properties Is Empty"))

                }

            }

        }
    }

    fun getOptionChild(option: Option )
    {

        viewModelScope.launch {

            _getOptionsChild.emit(Resource.loading(null))
            mainRepo.getOptionChild(option.id.toString()).collect{

                if (it.data.isNotEmpty())
                {
                    _getOptionsChild.emit(Resource.success(it))
                }else{
                    _getOptionsChild.emit(Resource.error(it,"Options Is Empty"))

                }
            }


        }
    }




}