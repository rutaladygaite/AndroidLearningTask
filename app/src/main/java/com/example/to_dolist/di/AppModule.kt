package com.example.to_dolist.di

import android.content.Context
import com.example.to_dolist.ToDoApplication
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjectionModule

@Module(
    includes = [
        AndroidInjectionModule::class,
        ActivityBindingModule::class,
]
)
abstract class AppModule {
    @Binds
    @AppContext
    abstract fun application(app: MyApp): Context
}