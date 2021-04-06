package com.example.to_dolist.di

import com.example.to_dolist.ToDoApplication
import com.example.to_dolist.di.modules.ActivityModule
import com.example.to_dolist.di.modules.AppModule

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
