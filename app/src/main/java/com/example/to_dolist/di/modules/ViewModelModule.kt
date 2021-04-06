package com.example.to_dolist.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.to_dolist.ToDoViewModel
import com.example.to_dolist.di.scopes.ViewModelKey

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ToDoViewModel::class)
    abstract fun bindToDoViewModel(viewModel: ToDoViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
