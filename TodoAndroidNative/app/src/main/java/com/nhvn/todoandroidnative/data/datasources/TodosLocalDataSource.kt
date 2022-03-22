package com.nhvn.todoandroidnative.data.datasources

import androidx.annotation.WorkerThread
import androidx.paging.Pager
import androidx.paging.PagingData
import com.nhvn.todoandroidnative.data.datasources.daos.TodoDao
import com.nhvn.todoandroidnative.data.datasources.models.Todo
import com.nhvn.todoandroidnative.data.repositories.AbstractTodosRepository
import kotlinx.coroutines.flow.Flow

class TodosLocalDataSource(
    private val todoDao: TodoDao
) {
    suspend fun getTodos(): List<Todo> {
        return todoDao.getAll();
    }

    suspend fun getTodosByPage(limit: Int, offset: Int): List<Todo> {
        return todoDao.getByPage(limit = limit, offset = offset);
    }


    val allTodos: Flow<List<Todo>> = todoDao.getAlphabetizedTodos()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(todo: Todo) {
        todoDao.insert(todo)
    }

//
//    override val todoPager: Pager<Int, Todo>
//        get() = TODO("Not yet implemented")
}