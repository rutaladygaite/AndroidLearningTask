package com.example.todolist.core.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.todolist.core.viewmodel.ToDoViewModel
import com.example.todolist.core.di.scopes.ViewModelKey
import com.example.todolist.core.viewmodel.ApiViewModel
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
    @IntoMap
    @ViewModelKey(ApiViewModel::class)
    abstract fun bindApiViewModel(viewModel: ApiViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}
