package com.example.to_dolist.di.modules

import android.app.Application
import dagger.Module
import dagger.Provides

@Module(
    includes = [
        ViewModelModule::class,
        DatabaseModule::class,
    ]
)
class AppModule(private val app: Application) {
    @Provides
    fun providesApplication(): Application = app
}
