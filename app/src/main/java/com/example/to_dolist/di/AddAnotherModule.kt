package com.example.to_dolist.di

import android.app.Activity
import com.example.to_dolist.AddAnotherActivity
import com.example.to_dolist.AddAnotherFragment
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
@Suppress("TooManyFunctions")
abstract class AddAnotherModule {

    @ContributesAndroidInjector
    abstract fun contributeAddAnotherFragment(): AddAnotherFragment

    @Binds
    abstract fun provideActivity(activity: AddAnotherActivity): Activity
}