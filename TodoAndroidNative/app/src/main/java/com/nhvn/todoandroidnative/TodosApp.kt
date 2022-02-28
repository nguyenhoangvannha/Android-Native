package com.nhvn.todoandroidnative

import android.app.Application
import androidx.room.Room
import com.nhvn.todoandroidnative.data.datasources.AppDatabase
import com.nhvn.todoandroidnative.data.datasources.TodosApiImpl
import com.nhvn.todoandroidnative.data.datasources.TodosLocalDataSource
import com.nhvn.todoandroidnative.data.datasources.TodosRemoteDataSource
import com.nhvn.todoandroidnative.data.repositories.TodosRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class TodosApp : Application() {
    // No need to cancel this scope as it'll be torn down with the process
    val applicationScope = CoroutineScope(SupervisorJob())

    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database by lazy { AppDatabase.getDatabase(this, applicationScope) }
    val todoRepository by lazy {
        TodosRepository(
            TodosRemoteDataSource(TodosApiImpl()),
            TodosLocalDataSource(database.todoDao())
        )
    }
}