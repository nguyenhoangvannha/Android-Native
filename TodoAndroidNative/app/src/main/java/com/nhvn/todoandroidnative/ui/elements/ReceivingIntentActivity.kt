package com.nhvn.todoandroidnative.ui.elements

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.nhvn.todoandroidnative.R

class ReceivingIntentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_receiving_intent)

        if (intent?.action == Intent.ACTION_DIAL) {
            val extras = intent.extras;
            findViewById<TextView>(R.id.txtText).text = extras.toString()
            Log.i("ReceivingIntentActivity", extras.toString());
        }
    }
}