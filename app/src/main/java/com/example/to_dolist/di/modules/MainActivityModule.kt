package com.example.to_dolist.di.modules

import android.app.Activity
import com.example.to_dolist.MainActivity
import com.example.to_dolist.ToDoFragment
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
