package com.nhvn.todoandroidnative.data.repositories

import com.nhvn.todoandroidnative.data.datasources.TodosLocalDataSource
import com.nhvn.todoandroidnative.data.datasources.TodosRemoteDataSource
import com.nhvn.todoandroidnative.data.datasources.models.TodoModel

abstract class AbstractTodosRepository() {
    abstract fun getTodos(): List<TodoModel>
}

class TodosRepository(
    private val todosRemoteDataSource: TodosRemoteDataSource, // network
    private val todosLocalDataSource: TodosLocalDataSource // database
) : AbstractTodosRepository() {
    override fun getTodos(): List<TodoModel> {
        var isNetworkAvailable = true;

        if (isNetworkAvailable) {
            return todosRemoteDataSource.getTodos();
        } else {
            return todosLocalDataSource.getTodos();
        }
    }
}