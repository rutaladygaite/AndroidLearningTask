package com.example.todolist.core.api

import javax.inject.Inject
import javax.inject.Provider

class ApiHelper @Inject constructor(private val informationApi: Provider<InformationApi>) {

    suspend fun getDadJoke() = informationApi.get().getDadJoke()

    suspend fun getChuckNorrisFact() = informationApi.get().getChuckNorrisFacts()

}
