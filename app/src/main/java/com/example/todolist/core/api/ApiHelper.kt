package com.example.todolist.core.api

class ApiHelper(private val informationApi: InformationApi) {

    suspend fun getDadJoke() = informationApi.getDadJoke()

}
