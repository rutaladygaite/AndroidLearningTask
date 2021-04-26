package com.example.todolist.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
abstract class ToDoDao {

    @Query("SELECT * FROM todo_item ")
    abstract fun getAll(): List<ToDoItem>

    @Query("SELECT * FROM todo_item")
    abstract fun getAllLiveData(): LiveData<List<ToDoItem>>

    @Insert
    abstract fun insert(title: List<ToDoItem>)

    @Insert
    abstract fun insertNew(vararg toDoItem: ToDoItem)

    @Update
    abstract fun updateList(vararg todo_item: ToDoItem)

//    @Query("UPDATE todo_item SET id = :id AND title = :title")
//    abstract fun updateToDo(id: Long, title: String)
}
