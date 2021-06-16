package com.example.todolist.core.di.modules

import com.example.todolist.app.*
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun contributesMainActivityTwo(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributesMainActivity(): ToDoActivity

    @ContributesAndroidInjector
    abstract fun contributesJokesActivity(): JokesActivity

    @ContributesAndroidInjector
    abstract fun contributesAddAnotherActivity(): AddAnotherActivity

    @ContributesAndroidInjector
    abstract fun contributesChangeDialog(): ChangeItemActivity

}
