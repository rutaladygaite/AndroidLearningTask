package com.example.todolist

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.database.ToDoState
import com.example.todolist.di.Injectable
import com.example.todolist.di.modules.ViewModelFactory
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber
import javax.inject.Inject


class MainActivity : AppCompatActivity(), HasAndroidInjector, Injectable {

    private val stateObserver = Observer<ToDoState> { bindState(it) }

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val toDoViewModel: ToDoViewModel
        get() = ViewModelProvider(this, viewModelFactory).get(ToDoViewModel::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.adapter = ToDoAdapter(listOf(), listOf())

        val button = findViewById<Button>(R.id.add_another_button)
        button.setOnClickListener {
            val intent = Intent(this, AddAnotherActivity::class.java)
            startActivity(intent)
        }
    }

    private fun bindState(state: ToDoState?){
        state ?: return

        // Make a few lists of items so when new ones are added it's repopulated and etc
        if(!state.toDoItems.isNullOrEmpty()){
            val adapter = recycler_view.adapter as ToDoAdapter
            val stateList = state.toDoItems
            val newList = mutableListOf<String>()
            val newListId = mutableListOf<Int>()
            for(listItems in stateList){
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
