package com.example.to_dolist.di

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [MyActivityModule::class])
    abstract fun myActivity(): MyActivity
}