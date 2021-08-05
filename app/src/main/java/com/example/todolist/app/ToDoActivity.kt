package com.example.todolist.app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.core.viewmodel.ToDoViewModel
import com.example.todolist.core.database.ToDoState
import com.example.todolist.core.di.Injectable
import com.example.todolist.core.di.modules.ViewModelFactory
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import kotlinx.android.synthetic.main.activity_to_do.*
import javax.inject.Inject

class ToDoActivity : AppCompatActivity(), HasAndroidInjector, Injectable {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val toDoViewModel: ToDoViewModel
        get() = ViewModelProvider(this, viewModelFactory).get(ToDoViewModel::class.java)

    private val stateObserver = Observer<ToDoState> { bindState(it) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_to_do)

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.adapter = ToDoAdapter(listOf(), listOf())

        val button = findViewById<Button>(R.id.add_another_button)
        button.setOnClickListener {
            val intent = Intent(this, AddAnotherActivity::class.java)
            startActivity(intent)
        }
    }

    private fun bindState(state: ToDoState?) {
        state ?: return

        if (!state.toDoItems.isNullOrEmpty()) {
            val adapter = recycler_view.adapter as ToDoAdapter
            val stateList = state.toDoItems
            val newList = mutableListOf<String>()
            val newListId = mutableListOf<Int>()
            for (listItems in stateList) {
                listItems.title?.let { newList.add(it) }
                listItems.id.let { newListId.add(it) }
            }
            adapter.notifyDataChange(newList, newListId)
        }
    }

    override fun onStart() {
        super.onStart()
        toDoViewModel.state.observe(this, stateObserver)
    }

    override fun onStop() {
        toDoViewModel.state.removeObservers(this)
        super.onStop()
    }

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector

}
