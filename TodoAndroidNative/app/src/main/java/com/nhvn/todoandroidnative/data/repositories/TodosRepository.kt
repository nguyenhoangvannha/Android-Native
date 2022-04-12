package com.nhvn.todoandroidnative.data.repositories

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.work.*
import com.codelab.android.datastore.UserPreferences
import com.nhvn.todoandroidnative.data.datasources.TodosLocalDataSource
import com.nhvn.todoandroidnative.data.datasources.TodosRemoteDataSource
import com.nhvn.todoandroidnative.data.datasources.datastore.PreferencesKeys
import com.nhvn.todoandroidnative.data.datasources.models.Todo
import com.nhvn.todoandroidnative.data.work.WORK_CHAIN_DATA_KEY
import com.nhvn.todoandroidnative.data.work.Worker1
import com.nhvn.todoandroidnative.data.work.Worker2
import com.nhvn.todoandroidnative.data.work.Worker3
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import java.time.Duration
import java.util.*

abstract class AbstractTodosRepository() {
    abstract suspend fun getTodos(): List<Todo>
    abstract suspend fun getTodosByPage(limit: Int = 7, offset: Int): List<Todo>
    abstract fun getTodosByPage(): Flow<PagingData<Todo>>
    abstract val allTodos: Flow<List<Todo>>
    abstract suspend fun insert(todo: Todo): Unit

    //abstract val todoPager: Pager<Int, Todo>
    abstract fun darkMode(): Flow<Boolean>

    abstract suspend fun setDarkMode(darkMode: Boolean)
    abstract fun userPreferencesFlow(): Flow<UserPreferences>

    abstract suspend fun setDarkModeProtoStore(darkMode: Boolean)
    abstract fun doAWorkChain()
}

class TodosRepository(
    private val todosRemoteDataSource: TodosRemoteDataSource, // network
    private val todosLocalDataSource: TodosLocalDataSource, // database
    private val todoPagingSource: PagingSource<Int, Todo>,
    private val userPreferencesStore: DataStore<Preferences>,
    private val userPreferencesProtoStore: DataStore<UserPreferences>,
//// This could be CoroutineScope(SupervisorJob() + Dispatchers.Default).
//    private val externalScope: CoroutineScope
) : AbstractTodosRepository() {
//    // Mutex to make writes to cached values thread-safe.
//    private val latestTodosMutex = Mutex()

    // Cache of the latest news got from the network.
    private var latestTodos: List<Todo> = emptyList()

    override suspend fun getTodos(): List<Todo> {
//        lateinit var result: List<Todo>;
//
//        var isNetworkAvailable = true;
//
//        if (isNetworkAvailable) {
//            externalScope.async {
//                todosRemoteDataSource.getTodos().also { networkResult ->
//                    // Thread-safe write to latestNews.
//                    latestTodosMutex.withLock {
//                        latestTodos = networkResult
//                    }
//                }
//            }.await()
//        } else {
//            result = todosLocalDataSource.getTodos();
//        }
//
//        latestTodosMutex.withLock {
//            this.latestTodos = result
//        }
//
//        return latestTodosMutex.withLock { this.latestTodos }
        return todosLocalDataSource.getTodos();
    }

    override suspend fun getTodosByPage(limit: Int, offset: Int): List<Todo> {
        val todos = todosLocalDataSource.getTodosByPage(limit = limit, offset = offset);
        Log.i("getTodosByPage", "todos.toString()");
        Log.i("getTodosByPage", todos.toString());
        return todos;
    }

    override fun getTodosByPage(): Flow<PagingData<Todo>> {
        return Pager<Int, Todo>(PagingConfig(pageSize = 6)) {
            todoPagingSource
        }.flow
    }

    override val allTodos: Flow<List<Todo>> = todosLocalDataSource.allTodos

    override suspend fun insert(todo: Todo) = todosLocalDataSource.insert(todo)

    override fun darkMode(): Flow<Boolean> {
        return userPreferencesStore.data.map { preferences ->
            preferences[PreferencesKeys.DARK_MODE] ?: false
        }
    }

    override suspend fun setDarkMode(darkMode: Boolean) {
        userPreferencesStore.edit { settings ->
            settings[PreferencesKeys.DARK_MODE] = darkMode
        }
    }

    override fun userPreferencesFlow(): Flow<UserPreferences> {
        return userPreferencesProtoStore.data
            .catch { exception ->
                // dataStore.data throws an IOException when an error is encountered when reading data
                if (exception is IOException) {
                    Log.e("Error", "Error reading sort order preferences.", exception)
                    emit(UserPreferences.getDefaultInstance())
                } else {
                    throw exception
                }
            }
    }

    override suspend fun setDarkModeProtoStore(darkMode: Boolean) {
        userPreferencesProtoStore.updateData { preferences ->
            preferences.toBuilder().setDarkMode(darkMode).build()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun doAWorkChain() {
        val worker1: OneTimeWorkRequest =
            OneTimeWorkRequestBuilder<Worker1>()
                .setInputData(workDataOf(Pair(WORK_CHAIN_DATA_KEY, "Input data 0 ")))
                .addTag("doAWorkChain.worker1")
                .addTag(WORK_CHAIN_TAG)
                .setInitialDelay(Duration.ofSeconds(2))
                .build()

        val worker2: OneTimeWorkRequest =
            OneTimeWorkRequestBuilder<Worker2>()
                .addTag("doAWorkChain.worker2")
                .addTag(WORK_CHAIN_TAG)
                .setInitialDelay(Duration.ofSeconds(2))
                .build()

        val worker3: OneTimeWorkRequest =
            OneTimeWorkRequestBuilder<Worker3>()
                .addTag("doAWorkChain.worker3")
                .addTag(WORK_CHAIN_TAG)
                .setInitialDelay(Duration.ofSeconds(2))
                .build()
        val workManager =
            WorkManager
                .getInstance();
        workManager.beginWith(worker1)
            .then(worker2)
            .then(worker3)
            .enqueue()
    }
//
//    override val todoPager: Pager<Int, Todo> =
//        Pager<Int, Todo>(PagingConfig(pageSize = 6)) {
//            todoPagingSource
//        };

    companion object {
        const val NETWORK_PAGE_SIZE = 50
    }
}

val WORK_CHAIN_TAG = "doAWorkChain ${Date()}";