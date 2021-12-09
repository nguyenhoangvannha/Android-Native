package com.nhvn.todoandroidnative.ui.screens

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.nhvn.todoandroidnative.models.TodoModel
import com.nhvn.todoandroidnative.ui.navigation.Routes
import com.nhvn.todoandroidnative.ui.state.rememberMyAppState
import com.nhvn.todoandroidnative.ui.viewmodel.TodoViewModel
import com.nhvn.todoandroidnative.ui.widgets.EditTodo
import com.nhvn.todoandroidnative.ui.widgets.ListTodo

@Composable
fun HomeScreen(navController: NavController, viewModel: TodoViewModel = viewModel()) {
    val uiState = viewModel.uiState

    val myAppState = rememberMyAppState()

    LaunchedEffect(key1 = "") {
    }

    DisposableEffect(key1 = "") {
        onDispose {
        }
    }

    MaterialTheme() {
        Scaffold(
            topBar = {
                TopAppBar {
                    Text(text = "Todos")
                }
            },

            floatingActionButton = {
                if (myAppState.shouldShowAddTodoFloatingActionButton) {
                    FloatingActionButton(onClick = {
                        navController.navigate(Routes.editTodoScreen)
                    }) {
                        Icon(Icons.Rounded.Add, contentDescription = "Localized description")
                    }
                }

            }
        ) {
            ListTodo(todos = uiState.todos)
            EditTodo(
                onSave = {
                    viewModel.addTodo(TodoModel(title = it))
                },
                onCancel = {
                })
        }
    }
}