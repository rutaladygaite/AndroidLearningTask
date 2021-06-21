package com.example.todolist.core.api

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {

    private var retrofit: Retrofit? = null
    private var gson = GsonBuilder()
        .setLenient()
        .create()

    private const val DAD_JOKES_URL = "https://icanhazdadjoke.com"
    private const val CHUCK_NORRIS_URL = "https://api.chucknorris.io"

    private fun getDadJokeRetrofit(): Retrofit? {
        retrofit = Retrofit.Builder()
            .baseUrl(DAD_JOKES_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        return retrofit
    }

    private fun getChuckFactRetrofit(): Retrofit? {
        retrofit = Retrofit.Builder()
            .baseUrl(CHUCK_NORRIS_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        return retrofit
    }

    val apiDadJokeService: InformationApi? =
        getDadJokeRetrofit()?.create(InformationApi::class.java)
    val apiChuckFactService: InformationApi? =
        getChuckFactRetrofit()?.create(InformationApi::class.java)

}
