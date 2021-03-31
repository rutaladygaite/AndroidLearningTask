package com.example.to_dolist.di

import com.example.to_dolist.AddAnotherActivity
import com.example.to_dolist.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun contributesMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [AddAnotherModule::class])
    abstract fun contributeAddAnotherActivity(): AddAnotherActivity
}
