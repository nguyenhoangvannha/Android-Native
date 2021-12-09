package com.nhvn.todoandroidnative.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.nhvn.todoandroidnative.models.TodoModel
import com.nhvn.todoandroidnative.ui.theme.TodoAndroidNativeTheme
import com.nhvn.todoandroidnative.ui.viewmodel.TodoViewModel
import com.nhvn.todoandroidnative.ui.widgets.EditTodo

@Composable
fun EditTodoScreen(navController: NavHostController, viewModel: TodoViewModel = viewModel()) {
    TodoAndroidNativeTheme {
        // A surface container using the 'background' color from the theme
        Surface(color = MaterialTheme.colors.background) {
            Scaffold(
                topBar = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back",
                            modifier = Modifier
                                .padding(16.dp)
                                .clickable {
                                    navController.popBackStack()
                                }
                        )
                        Text(text = "Edit Todo")
                    }
                },
                content = {
                    EditTodo(
                        onSave = {
                            viewModel.addTodo(TodoModel(title = it))
                            navController.popBackStack()
                        },
                        onCancel = {
                            navController.popBackStack()
                        }
                    )
                }
            )
        }
    }
}