package com.nhvn.todoandroidnative.ui.elements.widgets

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nhvn.todoandroidnative.data.datasources.models.Todo
import com.nhvn.todoandroidnative.data.datasources.models.TodoWorkingStateEnum

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TodoCard(
    todo: Todo,
    modifier: Modifier = Modifier,
    onNewTodoWorkingStateSelected: (Todo, TodoWorkingStateEnum) -> Unit = { _, _ -> },
    onClick: (todoId: String) -> Unit = { _ -> }
) {
//    var expanded by remember { mutableStateOf(false) }

    Card(modifier = modifier, onClick = {
        onClick(todo.id)
    }) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = todo.title)
            Text(text = todo.description)
            Text(text = todo.createdAt.toString())
            Text(text = todo.state.name)
//            Box(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .wrapContentSize(Alignment.TopStart)
//            ) {
//                Button(onClick = { expanded = true }) {
//                    Row() {
//                        Text(text = "Status: ${todo.state}")
//                        Icon(Icons.Default.MoreVert, contentDescription = "Localized description")
//                    }
//                }
//                DropdownMenu(
//                    expanded = expanded,
//                    onDismissRequest = { expanded = false }
//                ) {
//                    TodoWorkingStateEnum.values().map { state ->
//                        DropdownMenuItem(onClick = {
//                            expanded = false;
//                            onNewTodoWorkingStateSelected(todo, state)
//                        }) {
//                            Text(text = state.name)
//                        }
//                    }
//                }
//            }
        }
    }
}

@Composable
@Preview
fun PreviewTodoCard() {
    TodoCard(todo = Todo("id1", "Todo title 1", "Todo description"))
}