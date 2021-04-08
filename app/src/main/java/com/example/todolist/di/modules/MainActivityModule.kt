package com.example.todolist.di.modules

import android.app.Activity
import com.example.todolist.MainActivity
import com.example.todolist.ToDoFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityModule {

    @ContributesAndroidInjector
    abstract fun contributeToDoFragment(): ToDoFragment

    @Binds
    abstract fun provideActivity(activity: MainActivity): Activity
}
