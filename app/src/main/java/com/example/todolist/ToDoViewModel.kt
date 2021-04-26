package com.example.todolist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolist.database.ToDoDao
import com.example.todolist.database.ToDoItem
import com.example.todolist.database.ToDoState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class ToDoViewModel @Inject constructor(
    private val toDoDao: ToDoDao
) : ViewModel() {

    private val _state = MediatorLiveData<ToDoState>()
    // Our liveData from repository/dao
    private val toDoItemData = toDoDao.getAllLiveData()
    val state: LiveData<ToDoState> = _state

    init {
        // Generate init state of the state :zany-face:
        _state.value = generateInitState()
        // Add a source to spectate the data from DB
        _state.addSource(toDoItemData) { it ->
            // Update our state with the info from DB
            populateToDoItemState(it)
        }
    }

    private fun generateInitState(): ToDoState = ToDoState()

    private fun populateToDoItemState(toDoItem: List<ToDoItem>){
        // We update the state with our items that our fragment/activity will look at
        updateToDoState(toDoItem)
    }

    private fun updateToDoState(listOfItems: List<ToDoItem>?){
        updateState { copy(toDoItems = listOfItems)}
    }

    fun insertNewItem(message: String) {
        val todoItem = ToDoItem(0, message)
        toDoDao.insertNew(todoItem)
    }

    private fun updateState(update: ToDoState.() -> ToDoState) {
        _state.value = update(ToDoState())
    }
}
