package com.nhvn.todoandroidnative.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.nhvn.todoandroidnative.models.TodoModel

data class ExampleUiState(
    var todos: List<TodoModel> = emptyList(),
)

class TodoViewModel(
    private val savedState: SavedStateHandle
) : ViewModel() {

    var uiState by mutableStateOf<ExampleUiState>(ExampleUiState())

    fun addTodo(todo: TodoModel) {
        val newTodos = uiState.todos.toMutableList();
        newTodos.add(todo)
        uiState = uiState.copy(
            todos = newTodos
        )
    }
}