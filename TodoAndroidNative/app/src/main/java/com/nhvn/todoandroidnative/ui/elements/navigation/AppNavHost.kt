package com.nhvn.todoandroidnative.ui.elements.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.nhvn.todoandroidnative.ui.elements.screens.DemoScreen
import com.nhvn.todoandroidnative.ui.elements.screens.EditTodoScreen
import com.nhvn.todoandroidnative.ui.elements.screens.HomeScreen
import com.nhvn.todoandroidnative.ui.stateholders.viewmodel.TodoAndroidViewModel
import com.nhvn.todoandroidnative.ui.stateholders.viewmodel.TodoViewModel

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavHost(
    navController: NavHostController,
    startDestination: String = Routes.homeScreen,
    todoViewModel: TodoViewModel,
    todoAndroidViewModel: TodoAndroidViewModel
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Routes.homeScreen) {
            HomeScreen(
                todoViewModel = todoViewModel,
                navController = navController,
            )
        }
        composable(
            Routes.editTodoScreen("{todoId}")
        ) { backStackEntry ->
            EditTodoScreen(
                navController,
                todoId = backStackEntry.arguments?.getString("todoId") ?: "",
                viewModel = todoViewModel,
            )
        }
        composable(Routes.featuresScreen) {
            DemoScreen(todoViewModel, todoAndroidViewModel = todoAndroidViewModel)
        }
    }
}