package com.example.to_dolist.di

import android.app.Application
import dagger.Module
import dagger.Provides

@Module(
    includes = [
        ActivityModule::class,
        ViewModelModule::class,
    ]
)

class AppModule(private val app: Application){
    @Provides
    fun providesApplication(): Application = app
}
