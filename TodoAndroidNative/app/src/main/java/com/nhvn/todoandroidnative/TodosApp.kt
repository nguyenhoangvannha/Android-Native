package com.nhvn.todoandroidnative

import android.app.Application
import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Handler
import android.os.Looper
import androidx.core.os.HandlerCompat
import com.nhvn.todoandroidnative.data.datasources.*
import com.nhvn.todoandroidnative.data.datasources.datastore.dataStore
import com.nhvn.todoandroidnative.data.datasources.datastore.userPreferencesStore
import com.nhvn.todoandroidnative.data.datasources.paging.TodoPagingSource
import com.nhvn.todoandroidnative.data.repositories.TodosRepository
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import java.util.concurrent.*

@HiltAndroidApp
class TodosApp : Application() {}