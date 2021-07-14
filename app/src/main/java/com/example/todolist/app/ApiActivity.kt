@file:Suppress("DEPRECATION")

package com.example.todolist.app

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.todolist.R
import com.example.todolist.core.database.TextState
import com.example.todolist.core.di.modules.ViewModelFactory
import com.example.todolist.core.utils.Status
import com.example.todolist.core.viewmodel.ApiViewModel
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

    private val stateObserver = Observer<TextState> { bindState(it) }

    private val apiViewModel: ApiViewModel
        get() = ViewModelProvider(this, viewModelFactory).get(ApiViewModel::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_api)

        val buttonSelected = intent?.extras?.getString(BUTTON_SELECTED)
        val getApiTextButton = findViewById<Button>(R.id.get_api_text)
        val closeApiButton = findViewById<Button>(R.id.close_api)

        if (buttonSelected == CHUCK_INTENT) get_api_text.setText(R.string.chuck_fact_name)

        getApiTextButton.setOnClickListener {
            if (buttonSelected == CHUCK_INTENT) setupChuckObservers() else setupDadJokeObservers()
        }

        closeApiButton.setOnClickListener {
            super.finish()
        }
        apiViewModel.state.observe(this, stateObserver)
    }

    private fun bindState(state: TextState) {

        Timber.d("API state: $state")

        when (state.apiCallState) {
            Status.SUCCESS -> {
                progressBar.visibility = GONE
                apiText.text = state.apiText
            }
            Status.ERROR -> {
                progressBar.visibility = GONE
            }
            Status.LOADING -> {
                progressBar.visibility = VISIBLE
            }
        }
    }

    private fun setupDadJokeObservers() {
        apiViewModel.getDadJokeError().observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.ERROR -> {
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.SUCCESS -> {
                        apiViewModel.getItem("")
                    }
                    else -> {
                    }
                }
            }
        })
    }

    private fun setupChuckObservers() {
        apiViewModel.getChuckError().observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.ERROR -> {
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.SUCCESS -> {
                        apiViewModel.getItem("chuck")
                    }
                    else -> {
                    }
                }
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        apiViewModel.state.removeObservers(this)
    }

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector

    companion object {
        const val BUTTON_SELECTED = "false"
        const val CHUCK_INTENT = "chuck_fact"
    }
}
