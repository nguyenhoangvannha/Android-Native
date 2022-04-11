package com.nhvn.todoandroidnative.ui.elements.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Switch
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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.codelab.android.datastore.UserPreferences
import com.nhvn.todoandroidnative.data.datasources.models.TodoWorkingStateEnum
import com.nhvn.todoandroidnative.ui.elements.navigation.Routes
import com.nhvn.todoandroidnative.ui.stateholders.state.rememberMyAppState
import com.nhvn.todoandroidnative.ui.elements.theme.TodoAndroidNativeTheme
import com.nhvn.todoandroidnative.ui.stateholders.viewmodel.TodoViewModel
import com.nhvn.todoandroidnative.ui.elements.widgets.EditTodo
import com.nhvn.todoandroidnative.ui.elements.widgets.ListTodo
import com.nhvn.todoandroidnative.ui.elements.widgets.UserInfoList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    todoViewModel: TodoViewModel
) {

    val state = todoViewModel.allWords.observeAsState(initial = emptyList())
    val todos = state.value
    val darkModeState = todoViewModel.darkMode().collectAsState(initial = false)

    val userPreferencesState = todoViewModel.userPreferences()
        .collectAsState(initial = UserPreferences.getDefaultInstance())

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
                            Text(text = "Pref")
                            Switch(
                                checked = darkModeState.value,
                                onCheckedChange = {
                                    todoViewModel.setDarkMode(it)
                                },
                            )
                            Text(text = "Proto")
                            Switch(
                                checked = userPreferencesState.value.darkMode,
                                onCheckedChange = {
                                    todoViewModel.setDarkModeProtoStore(it)
                                },
                            )
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
                        navController.navigate(Routes.featuresScreen)
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
                            todos = todos,
                            modifier = Modifier.padding(16.dp),
                            onNewTodoWorkingStateSelected = { todo, newState ->
                                // todoViewModel.changeTodoWorkingState(todo, newState)
                            }
                        )
                        1 -> UserInfoList(
                            modifier = Modifier.padding(16.dp),
                            todoViewModel = todoViewModel,
                        )
                        2 -> ListTodo(
                            todos = todos
                                .filter { it.state == TodoWorkingStateEnum.done },
                            modifier = Modifier.padding(16.dp),
                            onNewTodoWorkingStateSelected = { todo, newState ->
                                // todoViewModel.changeTodoWorkingState(todo, newState)
                            }
                        )
                    }
                }


                EditTodo(
                    // modifier = Modifier.weight(1.0F),
                    onSave = {
                        todoViewModel.insert(it)
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

//@Preview
//@Composable
//fun PreviewHomeScreen() {
//    HomeScreen(navController = rememberNavController(), TodoViewModel(TodoViewModel()));
//}