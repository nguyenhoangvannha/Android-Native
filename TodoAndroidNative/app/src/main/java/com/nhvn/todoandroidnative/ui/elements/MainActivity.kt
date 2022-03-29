package com.nhvn.todoandroidnative.ui.elements

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.nhvn.todoandroidnative.TodosApp
import com.nhvn.todoandroidnative.ui.elements.observers.MyActivityLifecycleObserver
import com.nhvn.todoandroidnative.ui.stateholders.viewmodel.TodoViewModel
import com.nhvn.todoandroidnative.ui.stateholders.viewmodel.TodoViewModelFactory

const val EXTRA_MESSAGE = "com.nhvn.todoandroidnative.MESSAGE"
const val USERNOTE_STATE_KEY = "com.nhvn.todoandroidnative.ui.elements.USERNOTE_STATE_KEY"

class MainActivity : AppCompatActivity() {

    private val viewModel: TodoViewModel by viewModels {
        TodoViewModelFactory((application as TodosApp).todoRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycle.addObserver(MyActivityLifecycleObserver(lifecycle))

        setContent {
            MyApp(todoViewModel = viewModel);
        }
    }

}