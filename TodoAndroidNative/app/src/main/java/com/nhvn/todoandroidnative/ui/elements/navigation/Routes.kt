package com.nhvn.todoandroidnative.ui.elements.navigation

object Routes {
    const val homeScreen = "HomeScreen"
    fun editTodoScreen(todoId: String) = "EditTodoScreen?todoId=$todoId"
    const val featuresScreen = "featuresScreen"
}