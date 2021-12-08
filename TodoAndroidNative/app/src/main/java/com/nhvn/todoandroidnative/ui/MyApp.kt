package com.nhvn.todoandroidnative.ui

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.nhvn.todoandroidnative.ui.navigation.AppNavHost
import com.nhvn.todoandroidnative.ui.state.rememberMyAppState
import com.nhvn.todoandroidnative.ui.theme.TodoAndroidNativeTheme

@Composable
fun MyApp() {
    TodoAndroidNativeTheme() {
        val myAppState = rememberMyAppState()
        Scaffold(
            scaffoldState = myAppState.scaffoldState,
        ) {
            AppNavHost(navController = myAppState.navController)
        }
    }
}