package com.example.todolist.core.api

import com.example.todolist.core.database.ChuckNorrisItem
import com.example.todolist.core.database.DadJokeItem
import retrofit2.http.GET
import retrofit2.http.Headers

interface InformationApi {

    @GET("https://icanhazdadjoke.com")
    suspend fun getDadJoke(): DadJokeItem

    @GET("https://api.chucknorris.io/jokes/random")
    suspend fun getChuckNorrisFacts(): ChuckNorrisItem

}
