package com.nhvn.todoandroidnative.data.datasources

import com.nhvn.todoandroidnative.data.datasources.models.Todo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class TodosRemoteDataSource(
    private val newsApi: TodosApi,
//    private val ioDispatcher: CoroutineDispatcher
) {

    suspend fun getTodos(): List<Todo> {
//        =
//    // Move the execution to an IO-optimized thread since the ApiService
//        // doesn't support coroutines and makes synchronous requests.
//        withContext(ioDispatcher) {
//            newsApi.fetchLatestTodos()
//        }
        return newsApi.fetchLatestTodos();
    }

}

interface TodosApi {
    fun fetchLatestTodos(): List<Todo>
}

class TodosApiImpl : TodosApi {
    override fun fetchLatestTodos(): List<Todo> {
        TODO("Not yet implemented")
    }
}