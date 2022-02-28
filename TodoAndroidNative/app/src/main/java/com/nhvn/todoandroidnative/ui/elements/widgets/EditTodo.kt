package com.nhvn.todoandroidnative.ui.elements.widgets

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.nhvn.todoandroidnative.data.datasources.models.Todo

@Composable
fun EditTodo(
    modifier: Modifier = Modifier,
    onSave: (Todo) -> Unit = {},
    onCancel: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        val (title, setTitle) = remember { mutableStateOf("") }
        val (description, setDescription) = remember { mutableStateOf("") }

        TextField(
            value = title,
            modifier = Modifier
                .fillMaxWidth(),
            onValueChange = {
                setTitle(it)
            },
            label = { Text(text = "Title") },
        )
        TextField(
            value = description,
            modifier = Modifier
                .fillMaxWidth(),
            onValueChange = {
                setDescription(it)
            },
            label = { Text(text = "Description") },
        )
        Row() {
            Button(onClick = {
                onCancel()
                setTitle("")
                setDescription("")
            }) {
                Text(text = "Cancel")
            }
            Button(onClick = {
                onSave(Todo(title = title, description = description))
                setTitle("")
                setDescription("")
            }) {
                Text(text = "Save")
            }
        }
    }
}

@Preview
@Composable
fun PreviewEditTodo() {
    EditTodo()
}