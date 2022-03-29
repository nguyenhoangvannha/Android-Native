package com.nhvn.todoandroidnative.ui.elements.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.nhvn.todoandroidnative.ui.elements.screens.EditTodoScreen
import com.nhvn.todoandroidnative.ui.elements.screens.HomeScreen
import com.nhvn.todoandroidnative.ui.stateholders.viewmodel.TodoViewModel

@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: String = Routes.homeScreen,
    todoViewModel: TodoViewModel
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Routes.homeScreen) {
            HomeScreen(//navController,
                todoViewModel = todoViewModel
            )
        }
        composable(Routes.editTodoScreen) {
            EditTodoScreen(navController)
        }
    }
}