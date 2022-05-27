package com.nhvn.todoandroidnative.viewmodel

import androidx.lifecycle.asFlow
import com.nhvn.todoandroidnative.data.datasources.models.Todo
import com.nhvn.todoandroidnative.data.repositories.AbstractTodosRepository
import com.nhvn.todoandroidnative.ui.stateholders.viewmodel.TodoViewModel
import io.mockk.verifyOrder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.*

class TodoViewModelTest {
    val dispatcher = TestCoroutineDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun todoViewModel_Add_ReturnsTrue() {
        /* Given */
        val mock = mock<AbstractTodosRepository> {
            on { allTodos } doReturn emptyFlow()
        }

        val todoViewModel = TodoViewModel(todoRepository = mock)

        whenever(mock.add(any())).thenReturn("hello")

        /* When */
        val result = todoViewModel.add("hello")

        /* Then */
        assertTrue("hello" == result)
    }

    @Test
    fun todoViewModel_Insert_ReturnsTrue() = runBlocking {
        /* Given */
        val mock = mock<AbstractTodosRepository> {
            on { allTodos } doReturn emptyFlow()
        }

        val todoViewModel = TodoViewModel(todoRepository = mock)

        val todo = Todo(title = "Impl unit test")

        whenever(mock.allTodos).thenReturn(flow {
            emit(listOf(todo))
        })

        /* When */
        val firstItem = todoViewModel.todos.asFlow().first()

        /* Then */
        assertTrue(firstItem == listOf<Todo>(todo))
    }
}