package com.example.todolist.di.modules

import android.app.Application
import com.example.todolist.database.UIContext
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
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
    fun provideCoroutineScope(@UIContext uiContext: CoroutineContext): CoroutineScope = CoroutineScope(uiContext)
}
