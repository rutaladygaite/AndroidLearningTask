package com.example.todolist

import com.example.todolist.database.ToDoDao
import com.example.todolist.database.ToDoItem
import com.example.todolist.di.Injectable
import timber.log.Timber
import javax.inject.Inject

class Repository @Inject constructor(
    private val toDoDao: ToDoDao
) : Injectable {

    fun insertToDo(message: String) {
        val todoitem: ToDoItem = ToDoItem(1, message)
        toDoDao.insert(todoitem)
        Timber.d("Message: Repo $message")
    }
}