package com.example.todolist.core.api

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(
            chain.request()
                .newBuilder()
                .addHeader(
                    "User-Agent",
                    "Ruta test task https://github.com/rutaladygaite/AndroidLearningTask"
                )
                .addHeader("Accept", "application/json")
                .build()
        )
    }
}
