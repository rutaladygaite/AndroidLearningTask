package com.example.to_dolist

import android.app.Application
import com.example.to_dolist.di.AppComponent
import com.example.to_dolist.di.AppModule
import com.example.to_dolist.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import timber.log.Timber
import javax.inject.Inject

@Suppress("LateinitUsage", "TooGenericExceptionCaught")
open class ToDoApplication : Application(), HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = initializeComponent()
        appComponent.inject(this)
        Timber.plant(Timber.DebugTree())

    }

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector

    @Suppress("DEPRECATION")
    open fun initializeComponent(): AppComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
}
