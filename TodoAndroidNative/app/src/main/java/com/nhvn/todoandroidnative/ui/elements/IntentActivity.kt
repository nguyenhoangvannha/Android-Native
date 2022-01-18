package com.nhvn.todoandroidnative.ui.elements

import android.content.Intent
import android.content.Intent.ACTION_PICK
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import android.provider.ContactsContract
import android.util.Log
import android.view.View
import android.widget.Button
import com.nhvn.todoandroidnative.R
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
        findViewById<Button>(R.id.btnGetAlarms).setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                getAlarms()
            }
        })

        findViewById<Button>(R.id.btnGetContact).setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                getContact()
            }
        })
    }

    val REQUEST_SELECT_CONTACT = 2

    private fun getContact() {
        val intent = Intent().apply {
            action = ACTION_PICK
            type = ContactsContract.Contacts.CONTENT_TYPE
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivityForResult(intent, REQUEST_SELECT_CONTACT)
        } else {
            print("No Activity can handle getContact intent")
        }
    }

    val GET_ALARM_CODE = 1;

    private fun getAlarms() {
        val intent = Intent().apply {
            action = AlarmClock.ACTION_SHOW_ALARMS
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivityForResult(intent, GET_ALARM_CODE)
        } else {
            print("No Activity can handle getAlarms intent")
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == GET_ALARM_CODE && resultCode == RESULT_OK) {
            print("Result alarms ${data?.extras}")
        }
        when (requestCode) {
            (REQUEST_SELECT_CONTACT) -> {
                if (resultCode == RESULT_OK) {
                    val contactUri: Uri? = data?.data
                    Log.i("REQUEST_SELECT_CONTACT", "${contactUri}")
                } else {
                    Log.e("REQUEST_SELECT_CONTACT", "$resultCode ${data?.data}")
                }
            }
        }

    }

    private fun setAnAlarm() {
        val intent = Intent().apply {
            action = AlarmClock.ACTION_SET_ALARM
            putExtra(AlarmClock.EXTRA_HOUR, Date().hours + 1)
            putExtra(AlarmClock.EXTRA_MINUTES, Date().minutes + 1)
        }
        if (intent.resolveActivity(packageManager) != null) {
            val chooser: Intent = Intent.createChooser(intent, "Please choose an app(Forced)")
            startActivity(chooser)
        } else {
            print("No Activity can handle setAnAlarm intent")
        }
    }
}