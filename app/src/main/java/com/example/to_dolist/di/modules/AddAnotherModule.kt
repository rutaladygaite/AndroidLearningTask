package com.example.to_dolist.di.modules

import android.app.Activity
import com.example.to_dolist.AddAnotherActivity
import com.example.to_dolist.AddAnotherFragment
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
