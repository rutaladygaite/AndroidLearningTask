package com.example.to_dolist

import android.app.Application
import com.example.to_dolist.di.AppComponent
import com.example.to_dolist.di.AppInjector
import com.example.to_dolist.di.DaggerAppComponent
import com.example.to_dolist.di.modules.AppModule
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
