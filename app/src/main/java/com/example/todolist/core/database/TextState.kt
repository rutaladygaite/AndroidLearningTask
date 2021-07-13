package com.example.todolist.core.database

import com.example.todolist.core.utils.Status

data class TextState (
    val apiText : String? = null,
    val apiError: String? = null,
    var apiCallState : Status = Status.SUCCESS,
)