package com.example.todolist.database

import androidx.room.*

@Dao
abstract class ToDoDao {

    @Query("SELECT * FROM todo_item")
    abstract fun getAll(): List<ToDoItem>

    @Insert
    abstract fun insert(vararg todo_item: ToDoItem)

    @Update
    abstract fun updateTodo(vararg todo_item: ToDoItem)
}
