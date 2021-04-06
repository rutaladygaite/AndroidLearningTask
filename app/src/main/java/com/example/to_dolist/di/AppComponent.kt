package com.example.to_dolist.di

import com.example.to_dolist.ToDoApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Component(modules = [AppModule::class])
@Singleton
interface AppComponent : AndroidInjector<MyApp> {

    @Component.Factory
    interface Factory : AndroidInjector.Factory<MyApp> {
        override fun create(@BindsInstance instance: MyApp): AppComponent
    }
}