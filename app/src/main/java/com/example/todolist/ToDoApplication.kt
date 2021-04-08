package com.example.todolist

import android.app.Application
import com.example.todolist.di.AppComponent
import com.example.todolist.di.AppInjector
import com.example.todolist.di.DaggerAppComponent
import com.example.todolist.di.modules.AppModule
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import timber.log.Timber
import javax.inject.Inject

@Suppress("LateinitUsage")
open class ToDoApplication : Application(), HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = initializeComponent()
        appComponent.inject(this)
        AppInjector.init(this)
        Timber.plant(Timber.DebugTree())
    }

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector

    open fun initializeComponent(): AppComponent =
        DaggerAppComponent.builder().appModule(AppModule(this)).build()
}
