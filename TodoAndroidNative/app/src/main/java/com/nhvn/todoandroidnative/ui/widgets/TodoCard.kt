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

@Composable
fun TodoCard(title: String, description: String = "", onClick: () -> Unit) {
    Card(modifier = Modifier
        .clickable {
            onClick()
        }
        .fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = title)
            Text(text = description)
        }
    }
}

@Composable
@Preview
fun PreviewTodoCard() {
    TodoCard(title = "Go to the gym", description = "2021", onClick = {})
}