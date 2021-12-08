package com.nhvn.todoandroidnative.ui.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nhvn.todoandroidnative.models.TodoModel

@Composable
fun TodoCard(
    todo: TodoModel,
    modifier: Modifier = Modifier,
) {
    Card(modifier = modifier) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = todo.title)
            Text(text = todo.description)
        }
    }
}

@Composable
@Preview
fun PreviewTodoCard() {
    TodoCard(todo = TodoModel("id1", "Todo title 1", "Todo description"))
}