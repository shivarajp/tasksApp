package com.fauxiq.app.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fauxiq.app.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.title = "Tasks List"
    }
}
