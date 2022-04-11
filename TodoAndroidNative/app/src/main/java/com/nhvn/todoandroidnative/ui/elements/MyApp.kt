package com.nhvn.todoandroidnative.ui.elements

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import com.nhvn.todoandroidnative.ui.elements.navigation.AppNavHost
import com.nhvn.todoandroidnative.ui.stateholders.state.rememberMyAppState
import com.nhvn.todoandroidnative.ui.elements.theme.TodoAndroidNativeTheme
import com.nhvn.todoandroidnative.ui.stateholders.viewmodel.TodoViewModel

@Composable
fun MyApp(todoViewModel: TodoViewModel) {
    TodoAndroidNativeTheme() {
        val myAppState = rememberMyAppState()
        Scaffold(
            scaffoldState = myAppState.scaffoldState,
        ) {
            AppNavHost(navController = myAppState.navController, todoViewModel = todoViewModel)
        }
    }
}