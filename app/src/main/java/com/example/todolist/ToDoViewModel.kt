package com.example.todolist

import androidx.lifecycle.ViewModel
import com.example.todolist.database.ToDoDao
import com.example.todolist.database.ToDoItem
import timber.log.Timber
import javax.inject.Inject

class ToDoViewModel @Inject constructor(
    private val toDoDao: ToDoDao
) : ViewModel() {

    fun getToDoItem(): List<ToDoItem> {
        var getToDo = toDoDao.getAll()
        Timber.d("Message VM: $getToDo")
        return getToDo
    }

    fun insertNewItem(message: String) {
        val todoitem = ToDoItem(0, message)
        toDoDao.insertNew(todoitem)
    }
}
