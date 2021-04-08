package com.example.todolist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.example.todolist.database.ToDoState
import timber.log.Timber
import javax.inject.Inject

class ToDoViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _state = MediatorLiveData<ToDoState>()
    val state: LiveData<ToDoState> get() = _state

    init {
        _state.value = generateInitState()
    }

    private fun generateInitState(): ToDoState = ToDoState()

    private fun updateState(update: ToDoState.() -> ToDoState) {
        _state.value = update(getState())
    }

    fun test(text: String) {
        Timber.d("Message: Works! $text")
    }

    fun getToDoItem() {
        var getToDoItem = repository.getToDo()
        Timber.d("Message VM: $getToDoItem")
    }

    private fun getState(): ToDoState = _state.value ?: generateInitState()
}
