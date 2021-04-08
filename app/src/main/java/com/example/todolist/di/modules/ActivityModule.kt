package com.example.todolist.di.modules

import com.example.todolist.AddAnotherActivity
import com.example.todolist.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun contributesMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [AddAnotherModule::class])
    abstract fun contributesAddAnotherActivity(): AddAnotherActivity
}
