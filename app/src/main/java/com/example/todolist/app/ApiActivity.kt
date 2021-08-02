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
import com.example.todolist.Constants
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

        val getApiTextButton = findViewById<Button>(R.id.get_api_text)
        val closeApiButton = findViewById<Button>(R.id.close_api)

        val buttonSelected = intent?.extras?.getString(BUTTON_SELECTED) ?: Constants.CHUCK_FACT
        get_api_text.setText(
                if (buttonSelected == Constants.CHUCK_FACT) R.string.get_chuck_fact
                else R.string.get_dad_joke
        )
        getApiTextButton.setOnClickListener {
            setupObservers(buttonSelected)
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

    private fun setupObservers(apiType: String) {
        apiViewModel.getError(apiType).observe(this, {
            it?.let { resource ->
                when (resource.status) {
                    Status.ERROR -> {
                        get_api_text.isClickable = true
                        Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                    }
                    Status.SUCCESS -> {
                        get_api_text.isClickable = true
                    }
                    Status.LOADING -> {
                        get_api_text.isClickable = false
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
    }
}
