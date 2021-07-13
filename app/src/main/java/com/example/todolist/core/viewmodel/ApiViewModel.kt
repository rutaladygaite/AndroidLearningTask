package com.example.todolist.core.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todolist.core.api.ApiHelper
import com.example.todolist.core.database.ChuckNorrisItem
import com.example.todolist.core.database.DadJokeItem
import com.example.todolist.core.database.TextState
import com.example.todolist.core.utils.Status
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class ApiViewModel @Inject constructor(private val apiHelper: ApiHelper) : ViewModel() {

    private val _state = MediatorLiveData<TextState>()
    val state: LiveData<TextState> = _state

    init {
        _state.value = TextState()
    }

    fun getItem(type: String?) {
        loader()
        if (type == "chuck") updateChuckState() else updateJokeState()
    }

    private fun updateJokeState() {
        runBlocking {
            val getDadJoke = getDadJoke().joke
            _state.updateState { it.copy(apiText = getDadJoke, apiCallState = Status.SUCCESS) }
        }
    }

    private fun updateChuckState() {
        runBlocking {
            val getChuckFact = getChuckFact().value
            _state.updateState { it.copy(apiText = getChuckFact, apiCallState = Status.SUCCESS) }
        }
    }

    private fun loader() {
        _state.updateState {
            it.copy(apiCallState = Status.LOADING)
        }
    }

    private suspend fun getDadJoke(): DadJokeItem {
        return apiHelper.getDadJoke()
    }

    private suspend fun getChuckFact(): ChuckNorrisItem {
        return apiHelper.getChuckNorrisFact()
    }

    private inline fun MutableLiveData<TextState>.updateState(updateAction: (TextState) -> TextState) {
        value = updateAction(value ?: TextState())
    }

//        Loader icon liveData

//        fun getChuckFactLiveData() = liveData(Dispatchers.IO) {
//        emit(Resource.loading(data = null))
//        try {
//            emit(Resource.success(data = apiRepository.getChuckNorrisFact()))
//        } catch (exception: Exception) {
//            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
//        }
//    }
}
