package com.example.todolist

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.todolist.di.Injectable
import com.example.todolist.di.modules.ViewModelFactory
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class AddAnotherActivity: AppCompatActivity(), HasAndroidInjector, Injectable {

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
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    fun textInput() {
        var get_to_do_text = findViewById<TextView>(R.id.new_to_do_text)
        var to_do_text = get_to_do_text?.text.toString().trim()
        toDoViewModel.insertNewItem(to_do_text)
    }

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector
}