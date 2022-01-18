package com.nhvn.todoandroidnative.domain

import com.nhvn.todoandroidnative.data.datasources.TodosRemoteDataSource
import com.nhvn.todoandroidnative.data.datasources.models.TodoModel
import com.nhvn.todoandroidnative.data.repositories.AbstractTodosRepository
import com.nhvn.todoandroidnative.data.repositories.TodosRepository

class GetTodosUseCase(
    private val todosRepository: AbstractTodosRepository,
) {
    fun getTodos(): List<TodoModel> {
        return todosRepository.getTodos();
    }
}