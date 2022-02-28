package com.nhvn.todoandroidnative.data.repositories

import com.nhvn.todoandroidnative.data.datasources.TodosLocalDataSource
import com.nhvn.todoandroidnative.data.datasources.TodosRemoteDataSource
import com.nhvn.todoandroidnative.data.datasources.models.Todo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

abstract class AbstractTodosRepository() {
    abstract suspend fun getTodos(): List<Todo>
    abstract val allTodos: Flow<List<Todo>>
    abstract suspend fun insert(todo: Todo): Unit
}

class TodosRepository(
    private val todosRemoteDataSource: TodosRemoteDataSource, // network
    private val todosLocalDataSource: TodosLocalDataSource, // database
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

    override val allTodos: Flow<List<Todo>> = todosLocalDataSource.allTodos

    override suspend fun insert(todo: Todo) = todosLocalDataSource.insert(todo)
}