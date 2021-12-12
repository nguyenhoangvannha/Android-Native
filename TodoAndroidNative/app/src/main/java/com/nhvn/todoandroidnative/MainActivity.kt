package com.nhvn.todoandroidnative

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.nhvn.todoandroidnative.ui.MyApp
import com.nhvn.todoandroidnative.ui.navigation.AppNavHost

const val EXTRA_MESSAGE = "com.nhvn.todoandroidnative.MESSAGE"
const val USERNOTE_STATE_KEY = "com.nhvn.todoandroidnative.USERNOTE_STATE_KEY"

class MainActivity : ComponentActivity() {
    var userNote: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        userNote = savedInstanceState?.getString(USERNOTE_STATE_KEY)

        if (userNote != null)
            Log.i("onCreate", "$USERNOTE_STATE_KEY$userNote")

        setContent {
            val (title, setTitle) = remember { mutableStateOf(if (userNote != null) userNote else "") }
            Column() {
                MyApp(modifier = Modifier.weight(1F))
                TextField(value = title!!, onValueChange = {
                    setTitle(it)
                    userNote = it
                })
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.run {
            putString(USERNOTE_STATE_KEY, userNote)
        }
        if (userNote != null)
            Log.i("onSaveInstanceState", "$USERNOTE_STATE_KEY$userNote")
        super.onSaveInstanceState(outState)
    }

    // After onStart
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        userNote = savedInstanceState.getString(USERNOTE_STATE_KEY)
        if (userNote != null)
            Log.i("onRestoreInstanceState", "$USERNOTE_STATE_KEY$userNote")
        super.onRestoreInstanceState(savedInstanceState)
    }

    fun sendMessage(view: View) {
        val editText = findViewById<EditText>(R.id.editTextTextPersonName)
        val message = editText.text.toString();
        val intent = Intent(this, DisplayMessageActivity::class.java).apply {
            putExtra(EXTRA_MESSAGE, message)
        }
        startActivity(intent)
    }
}