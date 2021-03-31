package com.example.to_dolist.di

import android.app.Activity
import com.example.to_dolist.MainActivity
import com.example.to_dolist.ToDoFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
@Suppress("TooManyFunctions")
abstract class MainActivityModule {

    @ContributesAndroidInjector
    abstract fun contributeMainFragment(): ToDoFragment

    @Binds
    abstract fun provideActivity(activity: MainActivity): Activity
}
