package com.example.todolist.core.database

import com.example.todolist.core.utils.Status

data class TextState (
    var joke: String? = null,
    val text : String? = null,
    val apiError: String? = null,
    var apiCallState : Status = Status.SUCCESS,
    val statusError: Status = Status.ERROR,
)