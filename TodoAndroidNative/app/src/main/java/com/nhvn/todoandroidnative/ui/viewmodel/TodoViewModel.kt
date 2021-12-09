package com.nhvn.todoandroidnative.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.nhvn.todoandroidnative.models.TodoModel
import com.nhvn.todoandroidnative.models.TodoWorkingStateEnum

data class ExampleUiState(
    var todos: Map<String, TodoModel> = emptyMap(),
)

class TodoViewModel() : ViewModel() {
    var uiState by mutableStateOf<ExampleUiState>(ExampleUiState())

    fun addTodo(todo: TodoModel) {

        val newTodos = uiState.todos.toMutableMap()

        newTodos[todo.id] = todo

        uiState = uiState.copy(
            todos = newTodos
        )
    }

    fun changeTodoWorkingState(todo: TodoModel, newState: TodoWorkingStateEnum) {

        val newTodos = uiState.todos.toMutableMap()

        newTodos[todo.id] = todo.copy(state = newState)

        uiState = uiState.copy(
            todos = newTodos
        )
    }
}