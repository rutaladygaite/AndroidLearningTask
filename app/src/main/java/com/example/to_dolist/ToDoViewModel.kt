package com.example.to_dolist

import android.content.ClipData
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel

abstract class ToDoViewModel(
    private val repository: Repository,
) : ViewModel() {

    private val _state = MediatorLiveData<ToDoState>()
    val state: LiveData<ToDoState> get() = _state

    init {
        _state.value = generateInitState()
    }

    fun updateToDoItem(state: ToDoState) {
        _state.value = state
        updateState { copy(title = state.title) }
    }

    private fun generateInitState(): ToDoState = ToDoState()

    private fun updateState(update: ToDoState.() -> ToDoState) {
        _state.value = update(getState())
    }

    private fun getState(): ToDoState = _state.value ?: generateInitState()
}