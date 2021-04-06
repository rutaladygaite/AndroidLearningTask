package com.example.to_dolist.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@Singleton
class ViewModelFactory @Inject constructor(
    private val creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST", "UnsafeCast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val creator: ViewModel = creators.getValue(modelClass).get()
        return creator as T
    }
}
