package com.nhvn.todoandroidnative.ui.widgets

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.serpro69.kfaker.faker


@Composable
fun ListTodo(todos: List<String>) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier
            .padding(16.dp)
    ) {
        items(todos) { todo ->
            TodoCard(title = todo, onClick = {})
            Divider()
        }
    }
}

val faker = faker {}

@Preview
@Composable
fun PreviewListTodo() {
    ListTodo(todos = (1..11).map { faker.lorem.words() })
}