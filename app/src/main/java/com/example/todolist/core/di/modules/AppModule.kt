package com.example.todolist.core.di.modules

import android.app.Application
import androidx.annotation.VisibleForTesting
import com.example.todolist.core.api.HeaderInterceptor
import com.example.todolist.core.api.InformationApi
import com.example.todolist.core.di.scopes.BgContext
import com.example.todolist.core.di.scopes.UIContext
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.coroutines.CoroutineContext

@Module(
    includes = [
        ViewModelModule::class,
        DatabaseModule::class,
    ]
)
class AppModule(private val app: Application) {

    @Provides
    fun providesApplication(): Application = app

    @Provides
    @UIContext
    fun provideUIDispatcher(): CoroutineContext = Dispatchers.Main

    @Provides
    @BgContext
    fun provideBgDispatcher(): CoroutineContext = ioCoroutineContext

    @Provides
    fun provideCoroutineScope(@UIContext uiContext: CoroutineContext): CoroutineScope =
        CoroutineScope(uiContext)

    private val baseUrl = "https://localhost"

    @Provides
    fun provideApi(): InformationApi = Retrofit.Builder()
        .client(OkHttpClient.Builder().addInterceptor(HeaderInterceptor()).build())
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(InformationApi::class.java)

    companion object {
        @VisibleForTesting
        var ioCoroutineContext: CoroutineContext = Dispatchers.Default
    }
}
