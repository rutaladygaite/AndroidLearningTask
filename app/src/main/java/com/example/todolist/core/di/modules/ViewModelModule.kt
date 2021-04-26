package com.example.todolist.core.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.todolist.core.ToDoViewModel
import com.example.todolist.core.di.scopes.ViewModelKey

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
