package com.nhvn.todoandroidnative.ui.widgets

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun EditTodo(
    onSave: (String) -> Unit,
    onCancel: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        val (value, setValue) = remember { mutableStateOf("") }

        TextField(
            value = value,
            modifier = Modifier
                .fillMaxWidth(),
            onValueChange = {
                setValue(it)
            },
        )
        Row() {
            Button(onClick = onCancel) {
                Text(text = "Cancel")
            }
            Button(onClick = { onSave(value) }) {
                Text(text = "Save")
            }
        }
    }
}