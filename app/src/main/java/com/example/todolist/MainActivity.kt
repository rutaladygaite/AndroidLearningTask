package com.example.todolist

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.di.Injectable
import com.example.todolist.di.modules.ViewModelFactory
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import timber.log.Timber
import javax.inject.Inject


class MainActivity : AppCompatActivity(), HasAndroidInjector, Injectable {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val toDoViewModel: ToDoViewModel
        get() = ViewModelProvider(this, viewModelFactory).get(ToDoViewModel::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toDoList = printMessage()
        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.adapter = ToDoAdapter(toDoList)

        val button = findViewById<Button>(R.id.add_another_button)
        button.setOnClickListener {
            val intent = Intent(this, AddAnotherActivity::class.java)
            startActivity(intent)
        }
    }

    private fun printMessage(): ArrayList<String> {
        var toDoItem = toDoViewModel.getToDoItem()
        val numberOfInputs = toDoItem.count()
        val list = ArrayList<String>()
        if (toDoItem.isNotEmpty()) {
            for (n in 0..(numberOfInputs - 1)) {
                var toDoID = toDoItem[n].id
                var toDoTitle = toDoItem[n].title
                var fullText = "$toDoID. $toDoTitle"
                list.add(fullText)
                n + 1
            }
            Timber.d("Message full text: $list")
        }
        return list
    }

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector
}
