package com.example.todolist.app

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.todolist.R
import com.example.todolist.core.viewmodel.ToDoViewModel
import com.example.todolist.core.di.Injectable
import com.example.todolist.core.di.modules.ViewModelFactory
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class AddAnotherActivity : AppCompatActivity(), HasAndroidInjector, Injectable {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val toDoViewModel: ToDoViewModel
        get() = ViewModelProvider(this, viewModelFactory).get(ToDoViewModel::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_another_item)

        val button = findViewById<Button>(R.id.save_button)
        button.setOnClickListener {
            textInput()
            super.finish()
        }
    }

    private fun textInput() {
        val getToDoText = findViewById<TextView>(R.id.new_to_do_text)
        val getToDo = getToDoText?.text.toString().trim()
        toDoViewModel.insertNewItem(getToDo)
    }

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector

}
