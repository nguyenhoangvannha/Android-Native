package com.nhvn.todoandroidnative.domain

import com.nhvn.todoandroidnative.data.datasources.models.Todo
import com.nhvn.todoandroidnative.data.repositories.AbstractTodosRepository

class GetTodosUseCase(
    private val todosRepository: AbstractTodosRepository,
) {
//    suspend fun getTodos(): List<Todo> {
//        return todosRepository.getTodos();
//    }
}