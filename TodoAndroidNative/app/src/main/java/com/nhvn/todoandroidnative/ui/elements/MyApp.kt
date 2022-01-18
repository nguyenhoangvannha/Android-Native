package com.nhvn.todoandroidnative.ui

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.nhvn.todoandroidnative.ui.elements.navigation.AppNavHost
import com.nhvn.todoandroidnative.ui.stateholders.state.rememberMyAppState
import com.nhvn.todoandroidnative.ui.elements.theme.TodoAndroidNativeTheme

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