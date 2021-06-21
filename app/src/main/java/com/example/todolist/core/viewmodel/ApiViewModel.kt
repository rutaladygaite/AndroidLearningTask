package com.example.todolist.core.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.todolist.core.repository.ApiRepository
import com.example.todolist.core.utils.Resource
import kotlinx.coroutines.Dispatchers

class ApiViewModel(private val apiRepository: ApiRepository) : ViewModel() {

    fun getDadJoke() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = apiRepository.getDadJoke()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }

    fun getChuckFact() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = apiRepository.getChuckNorrisFact()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}
