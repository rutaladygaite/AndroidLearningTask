package com.example.to_dolist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import timber.log.Timber

class AddAnotherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_another_item)

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                .add(R.id.add_another_layout, AddAnotherFragment.newInstance()).commit()
            Timber.d("Message: main activity")
        }
    }
}
