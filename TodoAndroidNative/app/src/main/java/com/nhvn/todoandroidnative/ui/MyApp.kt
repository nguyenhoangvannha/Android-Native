package com.nhvn.todoandroidnative.ui

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.nhvn.todoandroidnative.ui.navigation.AppNavHost
import com.nhvn.todoandroidnative.ui.state.rememberMyAppState
import com.nhvn.todoandroidnative.ui.theme.TodoAndroidNativeTheme

@Composable
fun MyApp(modifier: Modifier) {
    TodoAndroidNativeTheme() {
        val myAppState = rememberMyAppState()
        Scaffold(
            scaffoldState = myAppState.scaffoldState,
            modifier = modifier
        ) {
            AppNavHost(navController = myAppState.navController)
        }
    }
}