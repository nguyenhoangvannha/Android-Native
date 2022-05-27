package com.nhvn.todoandroidnative.composable

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.nhvn.todoandroidnative.data.datasources.models.Todo
import com.nhvn.todoandroidnative.ui.elements.theme.TodoAndroidNativeTheme
import com.nhvn.todoandroidnative.ui.elements.widgets.TodoCard
import org.junit.Rule
import org.junit.Test

class TodoCardTest {
    @get:Rule
    val composeTestRule = createComposeRule()
    // use createAndroidComposeRule<YourActivity>() if you need access to
    // an activity

    @Test
    fun myTest() {
        // Start the app
        composeTestRule.setContent {
            TodoAndroidNativeTheme() {
                TodoCard(todo = Todo(title = "Impl compose unit test"))
            }
        }

        composeTestRule.onNodeWithText("Impl compose unit test").assertIsDisplayed()
    }
}