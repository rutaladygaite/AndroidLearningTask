package com.example.todolist

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.todolist.di.Injectable
import com.example.todolist.di.modules.ViewModelFactory
import javax.inject.Inject

class AddAnotherFragment : Fragment(), View.OnClickListener, Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val toDoViewModel: ToDoViewModel
        get() = ViewModelProvider(this, viewModelFactory).get(ToDoViewModel::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_add_another_item, container, false)
        val btn: Button = view.findViewById(R.id.save_button)
        btn.setOnClickListener(this)
        return view
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.save_button -> {
                textInput()
                val intent = Intent(getActivity(), MainActivity::class.java)
                getActivity()?.startActivity(intent)
            }
        }
    }

    fun textInput() {
        var get_to_do_text = view?.findViewById<TextView>(R.id.new_to_do_text)
        var to_do_text = get_to_do_text?.text.toString().trim()
        toDoViewModel.insertNewItem(to_do_text)
    }

    companion object {
        fun newInstance() = AddAnotherFragment()
    }
}
