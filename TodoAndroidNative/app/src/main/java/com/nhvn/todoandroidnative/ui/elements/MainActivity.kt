package com.nhvn.todoandroidnative.ui.elements

import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.nhvn.todoandroidnative.TodosApp
import com.nhvn.todoandroidnative.data.ForegroundService
import com.nhvn.todoandroidnative.data.datasources.MyBroadcastReceiver
import com.nhvn.todoandroidnative.ui.elements.observers.MyActivityLifecycleObserver
import com.nhvn.todoandroidnative.ui.stateholders.viewmodel.TodoAndroidViewModel
import com.nhvn.todoandroidnative.ui.stateholders.viewmodel.TodoViewModel
import com.nhvn.todoandroidnative.ui.stateholders.viewmodel.TodoViewModelFactory


const val EXTRA_MESSAGE = "com.nhvn.todoandroidnative.MESSAGE"
const val USERNOTE_STATE_KEY = "com.nhvn.todoandroidnative.ui.elements.USERNOTE_STATE_KEY"

class MainActivity : AppCompatActivity() {

    private val viewModel: TodoViewModel by viewModels {
        TodoViewModelFactory((application as TodosApp).todoRepository)
    }

    private val todoAndroidViewModel by viewModels<TodoAndroidViewModel>()


    private val br: BroadcastReceiver = MyBroadcastReceiver()

    private val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION).apply {
        addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
    }

//    val CHANNEL_DEFAULT_IMPORTANCE = "CHANNEL_DEFAULT_IMPORTANCE"

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//
//        val pendingIntent: PendingIntent =
//            Intent(this, MainActivity::class.java).let { notificationIntent ->
//                PendingIntent.getActivity(this, 0, notificationIntent, 0)
//            }
//
//        val notification: Notification = Notification.Builder(this, CHANNEL_DEFAULT_IMPORTANCE)
//            .setContentTitle("getText(R.string.notification_title)")
//            .setContentText("getText(R.string.notification_message)")
//            .setSmallIcon(R.drawable.ic_launcher_foreground)
//            .setContentIntent(pendingIntent)
//            .setTicker("getText(R.string.ticker_text)")
//            .build()
//
//        // Notification ID cannot be 0.
//        startForeground(ONGOING_NOTIFICATION_ID, notification)
//
//        val intent = Intent(...) // Build the intent for the service
//        applicationContext.startForegroundService(intent)

        lifecycle.addObserver(MyActivityLifecycleObserver(lifecycle))

        registerReceiver(br, filter)

        setContent {
            MyApp(todoViewModel = viewModel, todoAndroidViewModel = todoAndroidViewModel);
        }
    }


    override fun onDestroy() {
        unregisterReceiver(br)
        super.onDestroy()
    }

}