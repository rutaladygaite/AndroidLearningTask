@file:Suppress("DEPRECATION")

package com.example.todolist.app

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.todolist.R
import com.example.todolist.core.api.ApiHelper
import com.example.todolist.core.api.RetrofitBuilder
import com.example.todolist.core.di.Injectable
import com.example.todolist.core.di.modules.ApiViewModelFactory
import com.example.todolist.core.viewmodel.DadJokesViewModel
import com.example.todolist.core.di.modules.ViewModelFactory
import com.example.todolist.core.utils.Status
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import kotlinx.android.synthetic.main.activity_jokes.*
import timber.log.Timber
import javax.inject.Inject

class JokesActivity : AppCompatActivity(), HasAndroidInjector, Injectable {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: DadJokesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jokes)
        setupViewModel()
        setupObservers()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            ApiViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        ).get(DadJokesViewModel::class.java)
    }

    private fun setupObservers() {
        viewModel.getUsers().observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        apiText.text = resource.data?.joke
                        progressBar.visibility = View.GONE
                        Timber.d("API success, text: ${resource.data?.joke}")
                    }
                    Status.ERROR -> {
                        progressBar.visibility = View.GONE
                        Timber.d("API error, text: ${resource.data?.joke}")
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        progressBar.visibility = View.VISIBLE
                    }
                }
            }
        })
    }

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector

}
