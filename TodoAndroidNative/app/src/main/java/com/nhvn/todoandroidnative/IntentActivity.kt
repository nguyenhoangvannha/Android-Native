package com.nhvn.todoandroidnative

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import android.view.View
import android.widget.Button
import java.util.*

class IntentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent)
        findViewById<Button>(R.id.btnSetAlarm).setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                setAnAlarm()
            }
        })
    }

    private fun setAnAlarm() {
        val intent = Intent().apply {
            action = AlarmClock.ACTION_SET_ALARM
            putExtra(AlarmClock.EXTRA_HOUR, Date().hours + 1)
            putExtra(AlarmClock.EXTRA_MINUTES, Date().minutes + 1)
        }

        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        } else {
            print("No Activity can handle this intent")
        }
    }
}