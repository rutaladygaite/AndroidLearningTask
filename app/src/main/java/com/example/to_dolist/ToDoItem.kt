package com.example.to_dolist
import androidx.room.ColumnInfo
import androidx.room.Entity
import java.util.*

@Entity(tableName = "item")
data class ToDoItem(
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "time") val time: Date,
)
