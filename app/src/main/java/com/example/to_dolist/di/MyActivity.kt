package com.example.to_dolist.di

import androidx.activity.viewModels
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MyActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var factory: MyManualFactory

    val viewModel = ViewModelProvider(
        this,
        ViewModelFactory(factory, this, intent.extras)
    ).get(MyViewModel::class.java)
}

class MyManualFactory @Inject constructor(
) : ViewModelAssistedFactory<MyViewModel> {
    override fun create(stateHandle: SavedStateHandle) = MyViewModel(stateHandle)
}