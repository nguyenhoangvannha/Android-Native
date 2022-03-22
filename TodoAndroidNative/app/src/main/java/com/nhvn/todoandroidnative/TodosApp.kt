package com.nhvn.todoandroidnative

import android.app.Application
import com.nhvn.todoandroidnative.data.datasources.AppDatabase
import com.nhvn.todoandroidnative.data.datasources.TodosApiImpl
import com.nhvn.todoandroidnative.data.datasources.TodosLocalDataSource
import com.nhvn.todoandroidnative.data.datasources.TodosRemoteDataSource
import com.nhvn.todoandroidnative.data.datasources.datastore.dataStore
import com.nhvn.todoandroidnative.data.datasources.paging.TodoPagingSource
import com.nhvn.todoandroidnative.data.repositories.TodosRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class TodosApp : Application() {
    // No need to cancel this scope as it'll be torn down with the process
    private val applicationScope = CoroutineScope(SupervisorJob())

    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    private val database by lazy { AppDatabase.getDatabase(this, applicationScope) }

    private val todosLocalDataSource by lazy { TodosLocalDataSource(database.todoDao()) }

    val todoRepository by lazy {
        TodosRepository(
            TodosRemoteDataSource(TodosApiImpl()),
            todosLocalDataSource,
            TodoPagingSource(todosLocalDataSource),
            dataStore
        )
    }
}