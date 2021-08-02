package com.example.todolist.core.viewmodel

import androidx.lifecycle.*
import com.example.todolist.Constants
import com.example.todolist.core.api.InformationApi
import com.example.todolist.core.database.TextState
import com.example.todolist.core.utils.Resource
import com.example.todolist.core.utils.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

class ApiViewModel @Inject constructor(private val informationApi: Provider<InformationApi>) :
        ViewModel() {

    private val _state = MediatorLiveData<TextState>()
    val state: LiveData<TextState> = _state

    init {
        _state.value = TextState()
    }

    fun getError(apiType: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        loader()
        try {
            if (apiType == Constants.CHUCK_FACT) {
                val chuckNorrisFact = informationApi.get().getChuckNorrisFacts().value
                emit(Resource.success(data = chuckNorrisFact))
                updateApiText(chuckNorrisFact)
            } else {
                val dadJoke = informationApi.get().getDadJoke().joke
                emit(Resource.success(data = dadJoke))
                updateApiText(dadJoke)
            }
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
            getErrorMessage()
        }
    }

    private fun loader() {
        viewModelScope.launch {
            _state.updateState { it.copy(apiCallState = Status.LOADING) }
        }
    }

    private fun updateApiText(apiText: String) {
        viewModelScope.launch {
            _state.updateState { it.copy(apiText = apiText, apiCallState = Status.SUCCESS) }
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
