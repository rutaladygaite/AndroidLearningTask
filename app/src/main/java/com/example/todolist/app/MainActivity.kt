package com.example.todolist.app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.todolist.R
import com.example.todolist.core.di.Injectable

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toDoButton = findViewById<Button>(R.id.to_do_button)
        val jokesButton = findViewById<Button>(R.id.dad_jokes_button)

        toDoButton.setOnClickListener {
            val intent = Intent(this, ToDoActivity::class.java)
            startActivity(intent)
        }

        jokesButton.setOnClickListener {
            val intent = Intent(this, JokesActivity::class.java)
            startActivity(intent)
        }
    }
}
