package com.example.todolist.di.modules

import android.app.Activity
import com.example.todolist.MainActivity
import dagger.Binds
import dagger.Module

@Module
abstract class MainActivityModule {

    @Binds
    abstract fun provideActivity(activity: MainActivity): Activity
}
