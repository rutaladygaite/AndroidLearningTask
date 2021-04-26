package com.example.todolist.core.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [ToDoItem::class],
    version = 1,
    exportSchema = false
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun toDoDao(): ToDoDao
}
