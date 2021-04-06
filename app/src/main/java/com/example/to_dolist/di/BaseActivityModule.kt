package com.example.to_dolist.di

import androidx.appcompat.app.AppCompatActivity
import dagger.Binds
import dagger.Module

@Module
abstract class BaseActivityModule<A : AppCompatActivity> {
    @Binds
    abstract fun provideActivity(activity: A): AppCompatActivity
}