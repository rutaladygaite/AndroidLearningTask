package com.example.todolist.database

import androidx.room.*

@Dao
abstract class ToDoDao {

    @Query("SELECT * FROM todo_item")
    abstract fun getAll(): List<ToDoItem>

    @Insert
    abstract fun insert(title: List<ToDoItem>)

    @Query("UPDATE todo_item SET title = :title")
    abstract fun updateToDo(vararg title: String)
}
