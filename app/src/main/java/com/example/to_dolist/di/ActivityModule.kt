package com.example.to_dolist.di

import com.example.to_dolist.AddAnotherActivity
import com.example.to_dolist.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributeAddAnotherItem(): AddAnotherActivity
}
