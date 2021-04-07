package com.example.to_dolist.database

import androidx.room.*

@Dao
abstract class ToDoDao {

    @Query("SELECT * FROM todo_item")
    abstract fun getAll(): List<ToDoItem>

    @Insert
    abstract fun insertAll(vararg todo: ToDoItem)

    @Update
    abstract fun updateTodo(vararg todos: ToDoItem)
}
