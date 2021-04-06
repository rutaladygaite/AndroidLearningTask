package com.example.to_dolist.di

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import javax.inject.Inject

class MyViewModel @AssistedInject constructor(
    @Assisted private val stateHandle: SavedStateHandle,
) : ViewModel() {

    @dagger.assisted.AssistedFactory
    interface Factory : ViewModelAssistedFactory<MyViewModel>
}
