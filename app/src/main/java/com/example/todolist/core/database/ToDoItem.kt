package com.example.todolist.core.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Suppress("TooGenericExceptionCaught")
@Entity(tableName = "todo_item")
data class ToDoItem(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "title") val title: String?,
)
