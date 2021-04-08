package com.example.todolist

import com.example.todolist.database.ToDoDao
import com.example.todolist.di.Injectable
import timber.log.Timber
import javax.inject.Inject

class Repository @Inject constructor(
    private val toDoDao: ToDoDao
) : Injectable {

    fun getToDo() {
        var getToDo = toDoDao.getAll()
        Timber.d("Message in repo: $getToDo")
    }
}
