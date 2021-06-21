package com.example.todolist.core.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.todolist.core.api.ApiHelper
import com.example.todolist.core.repository.ApiRepository
import com.example.todolist.core.viewmodel.ApiViewModel

class ApiViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ApiViewModel::class.java)) {
            return ApiViewModel(ApiRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}
