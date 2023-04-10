package com.mazaady.task.presentation.fragments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mazaady.task.domain.repository.MainRepo
import com.mazaady.task.domain.repository.ShowUserRepo
import com.mazaady.task.presentation.fragments.main.MainViewModel
import javax.inject.Inject

class MainViewModelFactory @Inject constructor(private val mainRepo: MainRepo, private val showUserRepo: ShowUserRepo): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel( mainRepo = mainRepo ) as T
        }

        throw IllegalArgumentException("Unknown class name")

    }



}