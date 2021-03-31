package com.example.to_dolist.di

import com.example.to_dolist.ToDoApplication
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityModule::class,
        AppModule::class
    ]
)
interface AppComponent {
    fun inject(target: ToDoApplication)
}
