package com.nhvn.todoandroidnative.ui.elements.widgets

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nhvn.todoandroidnative.data.datasources.models.Todo
import com.nhvn.todoandroidnative.data.datasources.models.TodoWorkingStateEnum
import io.github.serpro69.kfaker.faker


@Composable
fun ListTodo(
    todos: List<Todo>,
    modifier: Modifier = Modifier,
    onNewTodoWorkingStateSelected: (Todo, TodoWorkingStateEnum) -> Unit = { _, _ -> },
    onItemClick: (todoId: String) -> Unit = { _ -> }
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        items(todos) { todo ->
            TodoCard(
                todo = todo,
                modifier = Modifier.fillMaxWidth(),
                onNewTodoWorkingStateSelected = onNewTodoWorkingStateSelected,
                onClick = onItemClick
            )
            Divider()
        }
    }
}

@Preview
@Composable
fun PreviewListTodo() {
    ListTodo(todos = (1..11).map { Todo("id1", "Todo title 1", "Todo description") })
}