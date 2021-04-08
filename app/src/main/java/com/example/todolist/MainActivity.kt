package com.example.todolist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.todolist.di.Injectable
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasAndroidInjector, Injectable {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.root_layout, ToDoFragment.newInstance()).commit()
        }
    }

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector
}
