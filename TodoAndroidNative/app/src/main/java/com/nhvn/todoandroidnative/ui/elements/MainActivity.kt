package com.nhvn.todoandroidnative.ui.elements

import android.os.Build
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.nhvn.todoandroidnative.ui.stateholders.viewmodel.TodoAndroidViewModel
import com.nhvn.todoandroidnative.ui.stateholders.viewmodel.TodoViewModel
import dagger.hilt.android.AndroidEntryPoint

const val EXTRA_MESSAGE = "com.nhvn.todoandroidnative.MESSAGE"
const val USERNOTE_STATE_KEY = "com.nhvn.todoandroidnative.ui.elements.USERNOTE_STATE_KEY"

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: TodoViewModel by viewModels()
    private val todoAndroidViewModel by viewModels<TodoAndroidViewModel>()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyApp(todoViewModel = viewModel, todoAndroidViewModel = todoAndroidViewModel);
        }
    }
}