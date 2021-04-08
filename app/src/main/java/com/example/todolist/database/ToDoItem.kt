package com.example.todolist.database
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo_item")
data class ToDoItem(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    @ColumnInfo(name = "title") val title: String,
 //   @ColumnInfo(name = "time") val time: Date,
)
