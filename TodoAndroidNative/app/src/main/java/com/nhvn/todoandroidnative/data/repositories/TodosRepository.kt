package com.nhvn.todoandroidnative.data.repositories

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.nhvn.todoandroidnative.data.datasources.TodosLocalDataSource
import com.nhvn.todoandroidnative.data.datasources.TodosRemoteDataSource
import com.nhvn.todoandroidnative.data.datasources.models.Todo
import kotlinx.coroutines.flow.Flow

abstract class AbstractTodosRepository() {
    abstract suspend fun getTodos(): List<Todo>
    abstract suspend fun getTodosByPage(limit: Int = 7, offset: Int): List<Todo>
    abstract val allTodos: Flow<List<Todo>>
    abstract suspend fun insert(todo: Todo): Unit
    abstract val todoPager: Pager<Int, Todo>
}

class TodosRepository(
    private val todosRemoteDataSource: TodosRemoteDataSource, // network
    private val todosLocalDataSource: TodosLocalDataSource, // database
    private val todoPagingSource: PagingSource<Int, Todo>,
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

    override val allTodos: Flow<List<Todo>> = todosLocalDataSource.allTodos

    override suspend fun insert(todo: Todo) = todosLocalDataSource.insert(todo)

    override val todoPager: Pager<Int, Todo> =
        Pager<Int, Todo>(PagingConfig(pageSize = 6)) {
            todoPagingSource
        };

    companion object {
        const val NETWORK_PAGE_SIZE = 50
    }
}