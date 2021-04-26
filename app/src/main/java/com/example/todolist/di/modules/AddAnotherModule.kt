package com.example.todolist.di.modules

import android.app.Activity
import com.example.todolist.AddAnotherActivity
import dagger.Binds
import dagger.Module

@Module
abstract class AddAnotherModule {

    @Binds
    abstract fun provideActivity(activity: AddAnotherActivity): Activity
}
