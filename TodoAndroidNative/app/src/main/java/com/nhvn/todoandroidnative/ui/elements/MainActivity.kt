package com.nhvn.todoandroidnative.ui.elements

import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.nhvn.todoandroidnative.TodosApp
import com.nhvn.todoandroidnative.data.datasources.MyBroadcastReceiver
import com.nhvn.todoandroidnative.ui.elements.observers.MyActivityLifecycleObserver
import com.nhvn.todoandroidnative.ui.stateholders.viewmodel.TodoViewModel
import com.nhvn.todoandroidnative.ui.stateholders.viewmodel.TodoViewModelFactory

const val EXTRA_MESSAGE = "com.nhvn.todoandroidnative.MESSAGE"
const val USERNOTE_STATE_KEY = "com.nhvn.todoandroidnative.ui.elements.USERNOTE_STATE_KEY"

class MainActivity : AppCompatActivity() {

    private val viewModel: TodoViewModel by viewModels {
        TodoViewModelFactory((application as TodosApp).todoRepository)
    }


    private val br: BroadcastReceiver = MyBroadcastReceiver()

    private val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION).apply {
        addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycle.addObserver(MyActivityLifecycleObserver(lifecycle))

        registerReceiver(br, filter)

        setContent {
            MyApp(todoViewModel = viewModel);
        }
    }

    override fun onDestroy() {
        unregisterReceiver(br)
        super.onDestroy()
    }

}