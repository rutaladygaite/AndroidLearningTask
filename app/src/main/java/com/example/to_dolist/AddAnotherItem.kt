package com.example.to_dolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import javax.inject.Inject
import timber.log.Timber

class AddAnotherItem : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_another_item)

        val save_button = findViewById<Button>(R.id.save_button)
        val to_do_text = findViewById<TextView>(R.id.new_to_do_text)

        save_button.setOnClickListener {
            val text = to_do_text.text.toString().trim()
            Timber.d("Message: $text")
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}