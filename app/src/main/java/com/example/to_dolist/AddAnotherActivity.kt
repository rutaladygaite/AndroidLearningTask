package com.example.to_dolist

import android.os.Bundle
import com.example.to_dolist.di.Injectable
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.DaggerAppCompatActivity
import timber.log.Timber
import javax.inject.Inject

class AddAnotherActivity : DaggerAppCompatActivity(), HasAndroidInjector, Injectable {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_another_item)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.add_another_layout, AddAnotherFragment.newInstance()).commit()
            Timber.d("Message: main activity")
        }
    }

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector
}
