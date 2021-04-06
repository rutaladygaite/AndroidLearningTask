package com.example.to_dolist.di.modules

import android.app.Application
import android.content.Context
import com.example.to_dolist.ToDoApplication
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.AndroidInjectionModule

@Module(
    includes = [
        ViewModelModule::class,
    ]
)
class AppModule(private val app: Application) {
    @Provides
    fun providesApplication(): Application = app
}
