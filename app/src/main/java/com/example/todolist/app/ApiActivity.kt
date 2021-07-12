@file:Suppress("DEPRECATION")

package com.example.todolist.app

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.todolist.R
import com.example.todolist.core.api.ApiHelper
import com.example.todolist.core.api.RetrofitBuilder
import com.example.todolist.core.database.TextState
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

class ApiActivity : AppCompatActivity(), HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: ApiViewModel
    private val stateObserver = Observer<TextState> { bindState(it) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_api)

        val dadJoke = intent?.extras?.getString(DAD_JOKE)
        val chuckFact = intent?.extras?.getString(CHUCK_FACT)

        if (dadJoke == "dad_joke") {
            viewModel = ViewModelProviders.of(
                this, ApiViewModelFactory(ApiHelper(RetrofitBuilder.apiDadJokeService!!))
            ).get(ApiViewModel::class.java)
        }

        if (chuckFact == "chuck_fact") {
            viewModel = ViewModelProviders.of(
                this, ApiViewModelFactory(ApiHelper(RetrofitBuilder.apiChuckFactService!!))
            ).get(ApiViewModel::class.java)
        }

        val getDadJokeButton = findViewById<Button>(R.id.get_dad_joke_item)
        val getChuckNorrisButton = findViewById<Button>(R.id.get_chuck_norris_item)
        val closeApiButton = findViewById<Button>(R.id.close_api)

        if (dadJoke == DAD_JOKE_INTENT) {
            getDadJokeButton.isVisible = true
        }
        if (chuckFact == CHUCK_INTENT) {
            getChuckNorrisButton.isVisible = true
        }

        getDadJokeButton.setOnClickListener {
//      setupChuckFactsObservers()
            viewModel.getItem("")
        }

        getChuckNorrisButton.setOnClickListener {
//      setupChuckFactsObservers()
            viewModel.getItem("chuck")
        }

        closeApiButton.setOnClickListener {
            super.finish()
        }
        viewModel.state.observe(this, stateObserver)
    }

    private fun bindState(state: TextState) {

        Timber.d("API state: $state")

        when (state.apiCallState) {
            Status.SUCCESS -> {
                progressBar.visibility = GONE
                apiTextDadJoke.text = state.joke
                apiTextChuck.text = state.text
            }
            Status.ERROR -> {
                progressBar.visibility = GONE
                Toast.makeText(this, state.apiError, Toast.LENGTH_LONG).show()
            }
            Status.LOADING -> {
                progressBar.visibility = VISIBLE
            }
        }
    }

//    Observer for loader icon

//    private fun setupChuckFactsObservers() {
//        viewModel.getChuckFactLiveData().observe(this, Observer {
//            it?.let { resource ->
//                when (resource.status) {
//                    Status.LOADING -> {
//                        progressBar.visibility = VISIBLE
//
//                    }
//                    else -> progressBar.visibility = GONE
//                }
//            }
//        })
//    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.state.removeObservers(this)
    }

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector

    companion object {
        const val DAD_JOKE = "false"
        const val CHUCK_FACT = "false"
        const val DAD_JOKE_INTENT = "dad_joke"
        const val CHUCK_INTENT = "chuck_fact"
    }
}
