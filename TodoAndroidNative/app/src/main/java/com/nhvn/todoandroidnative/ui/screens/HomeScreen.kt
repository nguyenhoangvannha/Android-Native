package com.nhvn.todoandroidnative.ui.screens

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.nhvn.todoandroidnative.ui.navigation.Routes
import com.nhvn.todoandroidnative.ui.widgets.ListTodo

@Composable
fun HomeScreen(todos: List<String>, navController: NavController) {
    MaterialTheme() {
        Scaffold(
            topBar = {
                TopAppBar {
                    Text(text = "Todos")
                }
            },

            floatingActionButton = {
                FloatingActionButton(onClick = {
                    navController.navigate(Routes.editTodoScreen)
                }) {
                    Icon(Icons.Rounded.Add, contentDescription = "Localized description")
                }
            }
        ) {
            ListTodo(todos = todos)
        }
    }
}