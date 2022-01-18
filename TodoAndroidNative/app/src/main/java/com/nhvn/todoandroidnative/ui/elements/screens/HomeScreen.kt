package com.nhvn.todoandroidnative.ui.elements.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.nhvn.todoandroidnative.data.datasources.models.TodoWorkingStateEnum
import com.nhvn.todoandroidnative.ui.elements.navigation.Routes
import com.nhvn.todoandroidnative.ui.stateholders.state.rememberMyAppState
import com.nhvn.todoandroidnative.ui.elements.theme.TodoAndroidNativeTheme
import com.nhvn.todoandroidnative.ui.stateholders.viewmodel.TodoViewModel
import com.nhvn.todoandroidnative.ui.elements.widgets.EditTodo
import com.nhvn.todoandroidnative.ui.elements.widgets.ListTodo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController, todoViewModel: TodoViewModel = viewModel()) {
    val uiState = todoViewModel.uiState

    val myAppState = rememberMyAppState()

    LaunchedEffect(key1 = "") {
    }

    var selectedTabIndex by remember { mutableStateOf(0) }

    val titles = listOf("All", "Inprocess", "Done")

    TodoAndroidNativeTheme() {
        Scaffold(
            topBar = {
                Column() {
                    CenterAlignedTopAppBar(
                        title = { Text(text = "Todos") },
                        navigationIcon = {
                            IconButton(onClick = { /* doSomething() */ }) {
                                Icon(
                                    imageVector = Icons.Filled.Menu,
                                    contentDescription = "Localized description"
                                )
                            }
                        },
                        actions = {
                            IconButton(onClick = { /* doSomething() */ }) {
                                Icon(
                                    imageVector = Icons.Filled.Favorite,
                                    contentDescription = "Localized description"
                                )
                            }
                        }
                    )

                    TabRow(selectedTabIndex = selectedTabIndex) {
                        titles.forEachIndexed { index, title ->
                            Tab(
                                text = { Text(title) },
                                selected = selectedTabIndex == index,
                                onClick = { selectedTabIndex = index }
                            )
                        }
                    }
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
            Column(
                modifier = Modifier.fillMaxHeight(),
            ) {
                Box(modifier = Modifier.weight(1F)) {
                    when (selectedTabIndex) {
                        0 -> ListTodo(
                            todos = uiState.todos.values.toList(),
                            modifier = Modifier.padding(16.dp),
                            onNewTodoWorkingStateSelected = { todo, newState ->
                                todoViewModel.changeTodoWorkingState(todo, newState)
                            }
                        )
                        1 -> ListTodo(
                            todos = uiState.todos.values.toList()
                                .filter { it.state == TodoWorkingStateEnum.inprocess },
                            modifier = Modifier.padding(16.dp),
                            onNewTodoWorkingStateSelected = { todo, newState ->
                                todoViewModel.changeTodoWorkingState(todo, newState)
                            }
                        )
                        2 -> ListTodo(
                            todos = uiState.todos.values.toList()
                                .filter { it.state == TodoWorkingStateEnum.done },
                            modifier = Modifier.padding(16.dp),
                            onNewTodoWorkingStateSelected = { todo, newState ->
                                todoViewModel.changeTodoWorkingState(todo, newState)
                            }
                        )
                    }
                }


                EditTodo(
                    // modifier = Modifier.weight(1.0F),
                    onSave = {
                        todoViewModel.addTodo(it)
                    },
                )
            }
        }
    }

    DisposableEffect(key1 = "") {
        onDispose {
        }
    }
}

@Preview
@Composable
fun PreviewHomeScreen() {
    HomeScreen(navController = rememberNavController(), TodoViewModel());
}