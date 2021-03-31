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

    lateinit var appComponent: AppComponent

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    fun activityInjector(): DispatchingAndroidInjector<Any> = this.dispatchingAndroidInjector

    override fun onCreate() {
        super.onCreate()
        appComponent = initializeComponent()
        appComponent.inject(this)
        Timber.plant(Timber.DebugTree())

    }

    open fun initializeComponent(): AppComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector
}
