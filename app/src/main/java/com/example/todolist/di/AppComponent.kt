package com.example.todolist.di

import com.example.todolist.ToDoApplication
import com.example.todolist.di.modules.ActivityModule
import com.example.todolist.di.modules.AppModule

import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ActivityModule::class,
    ]
)
interface AppComponent {
    fun inject(target: ToDoApplication)
}
