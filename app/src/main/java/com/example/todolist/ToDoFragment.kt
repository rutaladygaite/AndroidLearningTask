package com.example.todolist

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.todolist.di.Injectable
import com.example.todolist.di.modules.ViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber
import javax.inject.Inject

class ToDoFragment : Fragment(), View.OnClickListener, Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val toDoViewModel: ToDoViewModel
        get() = ViewModelProvider(this, viewModelFactory).get(ToDoViewModel::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_main, container, false)
        val btn: Button = view.findViewById(R.id.add_another_button)
        btn.setOnClickListener(this)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        printMessage()
    }

    private fun printMessage() {
        var toDoItem = toDoViewModel.getToDoItem()
        val numberOfInputs = toDoItem.count()
        if (toDoItem.isNotEmpty()) {
            var fulltext: String? = null
            for (n in 0..(numberOfInputs - 1)) {
                var toDoID = toDoItem[n].id
                var toDoTitle = toDoItem[n].title
                //In order not to display null value of fulltext
                if (fulltext == null) {
                    fulltext = toDoID.toString() + ". " + toDoTitle
                } else {
                    fulltext = fulltext + "\n" + toDoID + ". " + toDoTitle
                }
                n + 1
            }
            Timber.d("Message full text: \n$fulltext")
            text_displayed.text = fulltext
        } else {
            //Display when no objects exist yet
            text_displayed.text = ""
        }
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.add_another_button -> {
                val intent = Intent(requireActivity(), AddAnotherActivity::class.java)
                requireActivity().startActivity(intent)
            }
        }
    }

    companion object {
        fun newInstance() = ToDoFragment()
    }
}
