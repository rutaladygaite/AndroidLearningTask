package com.example.todolist.core.viewmodel

import androidx.lifecycle.*
import com.example.todolist.core.api.InformationApi
import com.example.todolist.core.database.ChuckNorrisItem
import com.example.todolist.core.database.DadJokeItem
import com.example.todolist.core.database.TextState
import com.example.todolist.core.utils.Resource
import com.example.todolist.core.utils.Status
import kotlinx.coroutines.*
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Provider

class ApiViewModel @Inject constructor(private val informationApi: Provider<InformationApi>) :
    ViewModel() {

    private val _state = MediatorLiveData<TextState>()
    val state: LiveData<TextState> = _state

    init {
        _state.value = TextState()
    }

    fun getItem(type: String?) {
        loader()
        if (type == "chuck") updateChuckFactText() else updateDadJokeText()
    }

    private fun updateDadJokeText() {
        viewModelScope.launch {
            val getChuckFact = getDadJoke().joke
            _state.updateState { it.copy(apiText = getChuckFact, apiCallState = Status.SUCCESS) }
        }
    }

    private fun updateChuckFactText() {
        viewModelScope.launch {
            val getDadJoke = getChuckFact().value
            _state.updateState { it.copy(apiText = getDadJoke, apiCallState = Status.SUCCESS) }
        }
    }

    private fun loader() {
        _state.updateState { it.copy(apiCallState = Status.LOADING) }
    }

    private suspend fun getDadJoke(): DadJokeItem {
        return informationApi.get().getDadJoke()
    }

    private suspend fun getChuckFact(): ChuckNorrisItem {
        return informationApi.get().getChuckNorrisFacts()
    }

    fun getDadJokeError() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = informationApi.get().getDadJoke()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
            getErrorMessage()
        }
    }

    fun getChuckError() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = informationApi.get().getChuckNorrisFacts()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
            getErrorMessage()
        }
    }

    private fun getErrorMessage() {
        viewModelScope.launch {
            _state.updateState { it.copy(apiCallState = Status.ERROR) }
        }
    }

    private inline fun MutableLiveData<TextState>.updateState(updateAction: (TextState) -> TextState) {
        value = updateAction(value ?: TextState())
    }

}
