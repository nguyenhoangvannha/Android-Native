package com.nhvn.todoandroidnative

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import com.nhvn.todoandroidnative.ui.widgets.ListTodo
import io.github.serpro69.kfaker.faker
import androidx.compose.material.Icon
import androidx.compose.material.icons.rounded.Add
import androidx.navigation.compose.rememberNavController
import com.nhvn.todoandroidnative.R
import com.nhvn.todoandroidnative.ui.navigation.AppNavHost

const val EXTRA_MESSAGE = "com.nhvn.todoandroidnative.MESSAGE"

class MainActivity : ComponentActivity() {
    private val faker = faker { }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val todos = (1..14).map { faker.lorem.words() }

        setContent {
            val navController = rememberNavController()
            AppNavHost(navController = navController, todos = todos)
        }
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