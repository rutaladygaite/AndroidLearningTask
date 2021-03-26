package com.example.to_dolist

import android.app.Application
import timber.log.Timber

open class ToDoApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
    }
}