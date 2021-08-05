package com.example.todolist.app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.todolist.Constants
import com.example.todolist.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toDoButton = findViewById<Button>(R.id.to_do_button)
        val jokesButton = findViewById<Button>(R.id.dad_jokes_button)
        val chuckButton = findViewById<Button>(R.id.chuck_norris_button)

        toDoButton.setOnClickListener {
            val intent = Intent(this, ToDoActivity::class.java)
            startActivity(intent)
        }

        jokesButton.setOnClickListener {
            startActivity(
                Intent(this, ApiActivity::class.java)
                    .putExtra(ApiActivity.BUTTON_SELECTED, Constants.DAD_JOKE)
            )
        }

        chuckButton.setOnClickListener {
            startActivity(
                Intent(this, ApiActivity::class.java)
                    .putExtra(ApiActivity.BUTTON_SELECTED, Constants.CHUCK_FACT)
            )
        }
    }
}
