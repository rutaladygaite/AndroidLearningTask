package com.example.todolist.app

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.todolist.R
import com.example.todolist.core.ToDoViewModel
import com.example.todolist.core.di.Injectable
import com.example.todolist.core.di.modules.ViewModelFactory
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import kotlinx.android.synthetic.main.item_selected.*
import javax.inject.Inject

class ChangeItemActivity : AppCompatActivity(), Injectable, HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val toDoViewModel: ToDoViewModel
        get() = ViewModelProvider(this, viewModelFactory).get(ToDoViewModel::class.java)

    @SuppressLint("SetTextI18n", "CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_selected)

        //Getting extras from activity
        val textSelected = intent?.extras?.getString(TEXT_SELECTED)
        val idSelected = intent?.extras?.getInt(TEXT_ID_SELECTED)

        //Adding selected text as a title
        selected_text_edit_field.text = textSelected

        //Adding editable text field with selected text in it
        val editText = findViewById<EditText>(R.id.selected_text)
        editText.text = Editable.Factory.getInstance().newEditable(textSelected)

        //Buttons
        val editButton = findViewById<Button>(R.id.edit_button)
        val deleteButton = findViewById<Button>(R.id.delete_button)
        val closeButton = findViewById<Button>(R.id.close_button)
        val cancelButton = findViewById<Button>(R.id.cancel_button)
        val applyButton = findViewById<Button>(R.id.apply_button)

        //Buttons functionality
        editButton.setOnClickListener {
            selected_text_edit_field.isVisible = false
            selected_text.isVisible = true
            editing_options_title.isVisible = false
            edit_text_buttons.isVisible = true
            edit_text_main_buttons.isVisible = false
        }
        deleteButton.setOnClickListener {
            toDoViewModel.deleteItem(idSelected!!, textSelected!!)
            super.finish()
        }
        closeButton.setOnClickListener {
            super.finish()
        }
        applyButton.setOnClickListener {
            val getToDoText = findViewById<TextView>(R.id.selected_text)
            val getToDo = getToDoText?.text.toString().trim()
            toDoViewModel.updateItem(idSelected!!, getToDo)
            super.finish()
        }
        cancelButton.setOnClickListener {
            selected_text_edit_field.isVisible = true
            selected_text.isVisible = false
            editing_options_title.isVisible = true
            edit_text_buttons.isVisible = false
            edit_text_main_buttons.isVisible = true
        }
    }

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector

    companion object {
        const val TEXT_SELECTED = "text_selected"
        const val TEXT_ID_SELECTED = "0"
    }
}
