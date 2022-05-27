package com.nhvn.todoandroidnative.composable

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import com.nhvn.todoandroidnative.data.datasources.models.Todo
import com.nhvn.todoandroidnative.ui.elements.MainActivity
import com.nhvn.todoandroidnative.ui.elements.theme.TodoAndroidNativeTheme
import com.nhvn.todoandroidnative.ui.elements.widgets.TodoCard
import org.junit.Rule
import org.junit.Test
import java.util.*

class TodoAppTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule(MainActivity::class.java)

    @Test
    fun myTest() {
        composeTestRule.onNodeWithTag("AddTodoFloatingActionButton").performClick();
        composeTestRule.onNodeWithTag("EditTodoScreen").assertIsDisplayed();
        composeTestRule.onNodeWithTag("EditTodoTitle")
            .performTextInput("Todo App ${Date()} EditTodoTitle");
        composeTestRule.onNodeWithTag("EditTodoDescription")
            .performTextInput("Todo App EditTodoDescription");
        composeTestRule.onNodeWithTag("EditTodoSaveButton").performClick();
    }
}