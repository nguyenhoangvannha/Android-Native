package com.nhvn.todoandroidnative.data.datasources

import android.util.Log
import androidx.annotation.WorkerThread
import androidx.paging.Pager
import androidx.paging.PagingData
import com.nhvn.todoandroidnative.data.datasources.daos.TodoDao
import com.nhvn.todoandroidnative.data.datasources.models.Todo
import com.nhvn.todoandroidnative.data.repositories.AbstractTodosRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TodosLocalDataSource @Inject constructor(
    private val todoDao: TodoDao
) : AbstractTodosRepository() {
    fun getTodos(): List<Todo> {
        return todoDao.getAll();
    }

    fun getTodosByPage(limit: Int, offset: Int): List<Todo> {
        return todoDao.getByPage(limit = limit, offset = offset);
    }


    override val allTodos: Flow<List<Todo>> = todoDao.getAlphabetizedTodos()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    override suspend fun insert(todo: Todo) {
        todoDao.insert(todo)
    }

//
//    override val todoPager: Pager<Int, Todo>
//        get() = TODO("Not yet implemented")
}