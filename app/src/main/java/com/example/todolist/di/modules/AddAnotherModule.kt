package com.example.todolist.di.modules

import android.app.Activity
import com.example.todolist.AddAnotherActivity
import com.example.todolist.AddAnotherFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AddAnotherModule {

    @ContributesAndroidInjector
    abstract fun contributeAddAnotherFragment(): AddAnotherFragment

    @Binds
    abstract fun provideActivity(activity: AddAnotherActivity): Activity
}
