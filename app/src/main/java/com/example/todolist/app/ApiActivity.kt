@file:Suppress("DEPRECATION")

package com.example.todolist.app

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.todolist.R
import com.example.todolist.core.api.ApiHelper
import com.example.todolist.core.api.RetrofitBuilder
import com.example.todolist.core.di.Injectable
import com.example.todolist.core.di.modules.ApiViewModelFactory
import com.example.todolist.core.viewmodel.ApiViewModel
import com.example.todolist.core.di.modules.ViewModelFactory
import com.example.todolist.core.utils.Status
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import kotlinx.android.synthetic.main.activity_api.*
import timber.log.Timber
import javax.inject.Inject

class ApiActivity : AppCompatActivity(), HasAndroidInjector, Injectable {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: ApiViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_api)

        val dadJoke = intent?.extras?.getString(DAD_JOKE)
        val chuckFact = intent?.extras?.getString(CHUCK_FACT)

        val getDadJokeButton = findViewById<Button>(R.id.get_dad_joke_item)
        val getChuckNorrisButton = findViewById<Button>(R.id.get_chuck_norris_item)
        val closeApiButton = findViewById<Button>(R.id.close_api)

        progressBar.visibility = View.GONE

        if (dadJoke == "dad_joke") {
            getDadJokeButton.isVisible = true
        }
        if (chuckFact == "chuck_fact") {
            getChuckNorrisButton.isVisible = true
        }

        getDadJokeButton.setOnClickListener {
            setupDadJokeViewModel()
            setupDadJokeObservers()
        }

        getChuckNorrisButton.setOnClickListener {
            setupChuckFactViewModel()
            setupChuckFactsObservers()
        }

        closeApiButton.setOnClickListener {
            super.finish()
        }
    }

    private fun setupDadJokeViewModel() {
        viewModel = ViewModelProviders.of(
            this, ApiViewModelFactory(ApiHelper(RetrofitBuilder.apiDadJokeService!!))
        ).get(ApiViewModel::class.java)
    }

    private fun setupChuckFactViewModel() {
        viewModel = ViewModelProviders.of(
            this, ApiViewModelFactory(ApiHelper(RetrofitBuilder.apiChuckFactService!!))
        ).get(ApiViewModel::class.java)
    }

    private fun setupDadJokeObservers() {
        viewModel.getDadJoke().observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        apiText.text = resource.data?.joke
                        progressBar.visibility = View.GONE
                        Timber.d("API Dad Joke success, text: ${resource.data?.joke}")
                    }
                    Status.ERROR -> {
                        progressBar.visibility = View.GONE
                        Timber.d("API Dad Joke error")
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        progressBar.visibility = View.VISIBLE
                    }
                }
            }
        })
    }

    private fun setupChuckFactsObservers() {
        viewModel.getChuckFact().observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        apiText.text = resource.data?.value
                        progressBar.visibility = View.GONE
                        Timber.d("API Chuck Fact success, text: ${resource.data?.value}")
                    }
                    Status.ERROR -> {
                        progressBar.visibility = View.GONE
                        Timber.d("API Chuck Fact error")
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

    companion object {
        const val DAD_JOKE = "false"
        const val CHUCK_FACT = "false"
    }
}
