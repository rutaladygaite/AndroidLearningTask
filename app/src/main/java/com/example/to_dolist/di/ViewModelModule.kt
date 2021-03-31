package com.example.to_dolist.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.to_dolist.ToDoViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("TooManyFunctions")
@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ToDoViewModel::class)
    abstract fun bindMainViewModel(viewModel: ToDoViewModel): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
