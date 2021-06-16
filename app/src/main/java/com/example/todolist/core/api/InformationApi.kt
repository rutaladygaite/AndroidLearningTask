package com.example.todolist.core.api

import com.example.todolist.core.database.DadJokeItem
import retrofit2.http.GET
import retrofit2.http.Headers

interface InformationApi {

    @GET("/")
    @Headers("Accept: application/json")
    suspend fun getDadJoke(): DadJokeItem

}
