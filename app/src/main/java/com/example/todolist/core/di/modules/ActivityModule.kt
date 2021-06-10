package com.example.todolist.core.di.modules

import com.example.todolist.app.AddAnotherActivity
import com.example.todolist.app.ChangeItemActivity
import com.example.todolist.app.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun contributesMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributesAddAnotherActivity(): AddAnotherActivity

    @ContributesAndroidInjector
    abstract fun contributesChangeDialog(): ChangeItemActivity
}
