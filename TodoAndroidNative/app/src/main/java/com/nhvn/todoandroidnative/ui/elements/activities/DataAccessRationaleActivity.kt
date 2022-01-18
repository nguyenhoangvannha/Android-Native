package com.nhvn.todoandroidnative.ui.elements.activities

import android.content.Intent.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.nhvn.todoandroidnative.R

class DataAccessRationaleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_access_rationale)
        val tvDataAccessRationale = findViewById<TextView>(R.id.tvDataAccessRationale)
        tvDataAccessRationale.text = "${intent.extras?.getString(EXTRA_PERMISSION_GROUP_NAME)}\n${
            intent.extras?.getString(EXTRA_ATTRIBUTION_TAGS)
        }\n${intent.extras?.getString(EXTRA_START_TIME)}\n" +
                "${intent.extras?.getString(EXTRA_END_TIME)}"
    }
}