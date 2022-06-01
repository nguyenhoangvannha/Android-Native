package com.nhvn.todoandroidnative.data.repositories

import android.os.Build
import android.os.Handler
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.work.*
import com.codelab.android.datastore.UserPreferences
import com.nhvn.todoandroidnative.data.datasources.TodosLocalDataSource
import com.nhvn.todoandroidnative.data.datasources.TodosRemoteDataSource
import com.nhvn.todoandroidnative.data.datasources.models.Todo
import com.nhvn.todoandroidnative.data.work.*
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.withContext
import java.time.Duration
import java.util.*
import java.util.concurrent.Executor
import javax.inject.Inject

abstract class AbstractTodosRepository() {
    abstract val allTodos: Flow<List<Todo>>
    abstract suspend fun insert(todo: Todo): Unit
    open fun add(value: String): String {
        return value;
    }

//    abstract suspend fun getTodos(): List<Todo>
//    abstract suspend fun getTodosByPage(limit: Int = 7, offset: Int): List<Todo>
//    abstract fun getTodosByPage(): Flow<PagingData<Todo>>
//
//
//    //abstract val todoPager: Pager<Int, Todo>
//    abstract fun darkMode(): Flow<Boolean>
//
//    abstract suspend fun setDarkMode(darkMode: Boolean)
//    abstract fun userPreferencesFlow(): Flow<UserPreferences>
//
//    abstract suspend fun setDarkModeProtoStore(darkMode: Boolean)
//    abstract fun doAWorkChain()
//    abstract fun cancelWorkByTag(tag: String)
//    abstract fun makeBackgroundThreadWorkRequest(
//        input: Int,
//        callback: (Result<Int>) -> Unit,
//    )
//
//    abstract suspend fun makeCoroutinesWorkRequest(
//        input: Int,
//        callback: (Result<Int>) -> Unit,
//    )
//
//    abstract fun doAForegroundWorker()
}

class TodosRepository @Inject constructor(
//    private val todosRemoteDataSource: TodosRemoteDataSource, // network
    private val todosLocalDataSource: AbstractTodosRepository, // database
//    private val todoPagingSource: PagingSource<Int, Todo>,
//    private val userPreferencesStore: DataStore<Preferences>,
//    private val userPreferencesProtoStore: DataStore<UserPreferences>,
//    private val executor: Executor,
//    private val resultHandler: Handler,

//// This could be CoroutineScope(SupervisorJob() + Dispatchers.Default).
//    private val externalScope: CoroutineScope
) : AbstractTodosRepository() {

    override val allTodos: Flow<List<Todo>> = todosLocalDataSource.allTodos

    override suspend fun insert(todo: Todo) = todosLocalDataSource.insert(todo)

//    // Mutex to make writes to cached values thread-safe.
//    private val latestTodosMutex = Mutex()

//    // Cache of the latest news got from the network.
//    private var latestTodos: List<Todo> = emptyList()
//
//    override suspend fun getTodos(): List<Todo> {
////        lateinit var result: List<Todo>;
////
////        var isNetworkAvailable = true;
////
////        if (isNetworkAvailable) {
////            externalScope.async {
////                todosRemoteDataSource.getTodos().also { networkResult ->
////                    // Thread-safe write to latestNews.
////                    latestTodosMutex.withLock {
////                        latestTodos = networkResult
////                    }
////                }
////            }.await()
////        } else {
////            result = todosLocalDataSource.getTodos();
////        }
////
////        latestTodosMutex.withLock {
////            this.latestTodos = result
////        }
////
////        return latestTodosMutex.withLock { this.latestTodos }
//        return todosLocalDataSource.getTodos();
//    }
//
//    override suspend fun getTodosByPage(limit: Int, offset: Int): List<Todo> {
//        val todos = todosLocalDataSource.getTodosByPage(limit = limit, offset = offset);
//        Log.i("getTodosByPage", "todos.toString()");
//        Log.i("getTodosByPage", todos.toString());
//        return todos;
//    }
//
//    override fun getTodosByPage(): Flow<PagingData<Todo>> {
//        return Pager<Int, Todo>(PagingConfig(pageSize = 6)) {
//            todoPagingSource
//        }.flow
//    }
//
//    override fun darkMode(): Flow<Boolean> {
////        return userPreferencesStore.data.map { preferences ->
////            preferences[PreferencesKeys.DARK_MODE] ?: false
////        }
//        return emptyFlow()
//    }
//
//    override suspend fun setDarkMode(darkMode: Boolean) {
////        userPreferencesStore.edit { settings ->
////            settings[PreferencesKeys.DARK_MODE] = darkMode
////        }
//    }
//
//    override fun userPreferencesFlow(): Flow<UserPreferences> {
////        return userPreferencesProtoStore.data
////            .catch { exception ->
////                // dataStore.data throws an IOException when an error is encountered when reading data
////                if (exception is IOException) {
////                    Log.e("Error", "Error reading sort order preferences.", exception)
////                    emit(UserPreferences.getDefaultInstance())
////                } else {
////                    throw exception
////                }
////            }
//        return emptyFlow()
//    }
//
//    override suspend fun setDarkModeProtoStore(darkMode: Boolean) {
////        userPreferencesProtoStore.updateData { preferences ->
////            preferences.toBuilder().setDarkMode(darkMode).build()
////        }
//    }
//
//    private val workManager =
//        WorkManager
//            .getInstance();
//
//    @RequiresApi(Build.VERSION_CODES.O)
//    override fun doAWorkChain() {
//        val worker1: OneTimeWorkRequest =
//            OneTimeWorkRequestBuilder<Worker1>()
//                .setInputData(workDataOf(Pair(WORK_CHAIN_DATA_KEY, "Overview, ")))
//                .addTag(WORKER1_TAG)
//                .addTag(WORK_CHAIN_TAG)
//                .setInitialDelay(Duration.ofSeconds(2))
//                .build()
//
//        val worker2: OneTimeWorkRequest =
//            OneTimeWorkRequestBuilder<Worker2>()
//                .addTag(WORKER2_TAG)
//                .addTag(WORK_CHAIN_TAG)
//                .setInitialDelay(Duration.ofSeconds(2))
//                .build()
//
//        val worker3: OneTimeWorkRequest =
//            OneTimeWorkRequestBuilder<Worker3>()
//                .addTag(WORKER3_TAG)
//                .addTag(WORK_CHAIN_TAG)
//                .setInitialDelay(Duration.ofSeconds(2))
//                .build()
//        workManager.beginWith(worker1)
//            .then(worker2)
//            .then(worker3)
//            .enqueue()
//    }
//
//    override fun cancelWorkByTag(tag: String) {
//        workManager.cancelAllWorkByTag(tag)
//    }
//
//    override fun makeBackgroundThreadWorkRequest(
//        input: Int,
//        callback: (Result<Int>) -> Unit,
//    ) {
//        executor.execute {
//            try {
//                val response = input + 100;
//                Thread.sleep(2000)
//                //callback(Result.Success(response))
//                resultHandler.post { callback(Result.Success(response)) }
//            } catch (e: Exception) {
//                val errorResult = Result.Error(e)
//                //callback(errorResult)
//                resultHandler.post { callback(errorResult) }
//            }
//        }
//    }
//
//    override suspend fun makeCoroutinesWorkRequest(input: Int, callback: (Result<Int>) -> Unit) {
//        // Move the execution of the coroutine to the I/O dispatcher
//        return withContext(Dispatchers.IO) {
//            // Blocking network request code
//            try {
//                val response = input + 100;
//                Thread.sleep(2000)
//                callback(Result.Success(response))
//                resultHandler.post { callback(Result.Success(response)) }
//            } catch (e: Exception) {
//                val errorResult = Result.Error(e)
//                callback(errorResult)
//            }
//        }
//    }
//
//    override fun doAForegroundWorker() {
//        val foregroundWorkRequest: WorkRequest =
//            OneTimeWorkRequestBuilder<ForegroundWorker>()
//                .setExpedited(OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST)
//                .build()
//
//        workManager.enqueue(foregroundWorkRequest)
//    }
//
////
////    override val todoPager: Pager<Int, Todo> =
////        Pager<Int, Todo>(PagingConfig(pageSize = 6)) {
////            todoPagingSource
////        };
//
//    companion object {
//        const val NETWORK_PAGE_SIZE = 50
//    }
}

val WORK_CHAIN_TAG = "doAWorkChain ${Date()}";
val WORKER1_TAG = "doAWorkChain.worker1"
val WORKER2_TAG = "doAWorkChain.worker2"
val WORKER3_TAG = "doAWorkChain.worker3"

sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
}