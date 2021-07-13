package com.example.todolist.core.api

import com.example.todolist.core.database.ChuckNorrisItem
import com.example.todolist.core.database.DadJokeItem
import retrofit2.http.GET
import retrofit2.http.Headers

interface InformationApi {

    @GET("/")
    @Headers("Accept: application/json", "User-Agent: Ruta test task https://github.com/rutaladygaite/AndroidLearningTask")
    suspend fun getDadJoke(): DadJokeItem

    @GET("https://api.chucknorris.io/jokes/random")
    @Headers("Accept: application/json", "User-Agent: Ruta test task https://github.com/rutaladygaite/AndroidLearningTask")
    suspend fun getChuckNorrisFacts(): ChuckNorrisItem

}
