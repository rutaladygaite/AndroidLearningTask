package com.example.todolist.core.viewmodel

import androidx.lifecycle.*
import com.example.todolist.Constants
import com.example.todolist.core.api.InformationApi
import com.example.todolist.core.database.TextState
import com.example.todolist.core.utils.Resource
import com.example.todolist.core.utils.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Provider

class ApiViewModel @Inject constructor(private val informationApi: Provider<InformationApi>) :
        ViewModel() {

    private val _state = MediatorLiveData<TextState>()
    val state: LiveData<TextState> = _state

    init {
        _state.value = TextState()
    }

    fun getItem(apiType: String) {
        Timber.d("API Get Item $apiType")
        viewModelScope.launch {
            loader()
            _state.updateState { it.copy(apiText = apiType, apiCallState = Status.SUCCESS) }
        }
    }

    private fun loader() {
        _state.updateState { it.copy(apiCallState = Status.LOADING) }
    }

    fun getError(apiType: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            if (apiType == Constants.CHUCK_FACT) emit(Resource.success(data = informationApi.get().getChuckNorrisFacts().value))
            else emit(Resource.success(data = informationApi.get().getDadJoke().joke))
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
