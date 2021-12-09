package com.nhvn.todoandroidnative.ui.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nhvn.todoandroidnative.models.TodoModel
import com.nhvn.todoandroidnative.models.TodoWorkingStateEnum

@Composable
fun TodoCard(
    todo: TodoModel,
    modifier: Modifier = Modifier,
    onNewTodoWorkingStateSelected: (TodoModel, TodoWorkingStateEnum) -> Unit = { _, _ -> }
) {
    var expanded by remember { mutableStateOf(false) }

    Card(modifier = modifier) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = todo.title)
            Text(text = todo.description)
            Text(text = "Created: ${todo.createdAt}")
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.TopStart)
            ) {
                Button(onClick = { expanded = true }) {
                    Row() {
                        Text(text = "Status: ${todo.state}")
                        Icon(Icons.Default.MoreVert, contentDescription = "Localized description")
                    }
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    TodoWorkingStateEnum.values().map { state ->
                        DropdownMenuItem(onClick = {
                            expanded = false;
                            onNewTodoWorkingStateSelected(todo, state)
                        }) {
                            Text(text = state.name)
                        }
                    }
                }
            }
        }
    }
}

@Composable
@Preview
fun PreviewTodoCard() {
    TodoCard(todo = TodoModel("id1", "Todo title 1", "Todo description"))
}