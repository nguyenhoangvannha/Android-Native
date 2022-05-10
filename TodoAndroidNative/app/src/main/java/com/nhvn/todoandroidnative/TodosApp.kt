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
class TodosApp : Application() {
//    // No need to cancel this scope as it'll be torn down with the process
//    private val applicationScope = CoroutineScope(SupervisorJob())
//
//    // Using by lazy so the database and the repository are only created when they're needed
//    // rather than when the application starts
//    private val database by lazy { AppDatabase.getDatabase(this) }
//
//    private val todosLocalDataSource by lazy { TodosLocalDataSource(database.todoDao()) }

    //    private val executorService: ExecutorService = Executors.newFixedThreadPool(4)
//
//    private val mainThreadHandler: Handler = HandlerCompat.createAsync(Looper.getMainLooper())
//
//    /*
//     * Gets the number of available cores
//     * (not always the same as the maximum number of cores)
//     */
//    private val NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors()
//
//    // Instantiates the queue of Runnables as a LinkedBlockingQueue
//    private val workQueue: BlockingQueue<Runnable> =
//        LinkedBlockingQueue<Runnable>()
//
//    // Sets the amount of time an idle thread waits before terminating
//    private val KEEP_ALIVE_TIME = 1L
//
//    // Sets the Time Unit to seconds
//    private val KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS
//
//    // Creates a thread pool manager
//    private val threadPoolExecutor: ThreadPoolExecutor = ThreadPoolExecutor(
//        NUMBER_OF_CORES,       // Initial pool size
//        NUMBER_OF_CORES,       // Max pool size
//        KEEP_ALIVE_TIME,
//        KEEP_ALIVE_TIME_UNIT,
//        workQueue
//    )
//
//    val todoRepository by lazy {
//        TodosRepository(
//            TodosRemoteDataSource(TodosApiImpl()),
//            todosLocalDataSource,
//            TodoPagingSource(todosLocalDataSource),
//            dataStore,
//            userPreferencesProtoStore = userPreferencesStore,
//            executor = threadPoolExecutor,
//            resultHandler = mainThreadHandler,
//        )
//    }
    private val br: BroadcastReceiver = MyBroadcastReceiver()

    private val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION).apply {
        addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
    }

    override fun onCreate() {
        registerReceiver(br, filter)
        super.onCreate()
    }

    override fun onTerminate() {
        unregisterReceiver(br)
        super.onTerminate()
    }
}