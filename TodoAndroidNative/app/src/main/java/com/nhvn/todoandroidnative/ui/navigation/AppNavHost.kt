package com.nhvn.todoandroidnative.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.nhvn.todoandroidnative.ui.screens.EditTodoScreen
import com.nhvn.todoandroidnative.ui.screens.HomeScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: String = Routes.homeScreen,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Routes.homeScreen) {
            HomeScreen(navController)
        }
        composable(Routes.editTodoScreen) {
            EditTodoScreen(navController)
        }
    }
}