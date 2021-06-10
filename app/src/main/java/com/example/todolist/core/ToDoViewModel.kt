package com.example.todolist.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.example.todolist.core.database.ToDoDao
import com.example.todolist.core.database.ToDoItem
import com.example.todolist.core.database.ToDoState
import timber.log.Timber
import javax.inject.Inject

class ToDoViewModel @Inject constructor(
    private val toDoDao: ToDoDao
) : ViewModel() {

    private val toDoItemData = toDoDao.getAllLiveData()

    private val _state = MediatorLiveData<ToDoState>()
    val state: LiveData<ToDoState> = _state

    init {
        _state.value = generateInitState()
        _state.addSource(toDoItemData) {
            populateToDoItemState(it)
        }
    }

    private fun generateInitState(): ToDoState = ToDoState()

    private fun populateToDoItemState(toDoItem: List<ToDoItem>) {
        updateToDoState(toDoItem)
    }

    private fun updateToDoState(listOfItems: List<ToDoItem>?) {
        updateState { copy(toDoItems = listOfItems) }
    }

    fun insertNewItem(message: String) {
        val todoItem = ToDoItem(0, message)
        toDoDao.insertNew(todoItem)
    }

    fun deleteItem(id: Int, message: String){
        val todoItem = ToDoItem(id, message)
        toDoDao.deleteItem(todoItem)
    }

    fun updateItem(id: Int, message: String){
        val todoItem = ToDoItem(id, message)
        toDoDao.updateList(todoItem)
    }

    private fun updateState(update: ToDoState.() -> ToDoState) {
        _state.value = update(ToDoState())
    }
}
