package com.example.todolist.core.repository

import com.example.todolist.core.api.ApiHelper

class ApiRepository(private val apiHelper: ApiHelper) {

    suspend fun getDadJoke() = apiHelper.getDadJoke()

}
