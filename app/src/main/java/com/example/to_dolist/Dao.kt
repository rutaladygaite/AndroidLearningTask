package com.example.to_dolist

import androidx.room.Dao
import androidx.room.Query

@Dao
abstract class Dao {

    @Query("UPDATE item SET title = :title")
    abstract fun setTitle(title: String)
}
