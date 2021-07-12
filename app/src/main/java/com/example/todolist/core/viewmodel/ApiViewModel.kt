package com.example.todolist.core.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todolist.core.database.ChuckNorrisItem
import com.example.todolist.core.database.DadJokeItem
import com.example.todolist.core.database.TextState
import com.example.todolist.core.repository.ApiRepository
import com.example.todolist.core.utils.Status
import kotlinx.coroutines.runBlocking

class ApiViewModel(private val apiRepository: ApiRepository) : ViewModel() {

    private val _state = MediatorLiveData<TextState>()
    val state: LiveData<TextState> = _state

    init {
        _state.value = TextState()
    }

    private fun updateJokeState() {
        runBlocking {
            getDadJoke()
            val getDadJoke = getDadJoke().joke
            _state.updateState {
                it.copy(joke = getDadJoke, apiCallState = Status.SUCCESS)
            }
        }
    }

    private fun updateChuckState() {
        runBlocking {
            getChuckFact()
            val getChuckFact = getChuckFact().value
            _state.updateState {
                it.copy(text = getChuckFact, apiCallState = Status.SUCCESS)
            }
        }
    }

    private fun loader() {
        _state.updateState {
            it.copy(apiCallState = Status.LOADING)
        }
    }

    private suspend fun getDadJoke(): DadJokeItem {
        return apiRepository.getDadJoke()
    }

    private suspend fun getChuckFact(): ChuckNorrisItem {
        return apiRepository.getChuckNorrisFact()
    }

    fun getItem(type: String?) {
        loader()
        if (type == "chuck") updateChuckState() else updateJokeState()
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
