package com.example.androidbasiccompose.compose.practice

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androidbasiccompose.R
import com.example.androidbasiccompose.ui.theme.AndroidBasicComposeTheme

@Composable
fun TaskManager(title: String, des: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CompletedImage()
        CompletedText(title)
        TSubText(des)
    }
}

@Composable
fun TSubText(des: String, modifier: Modifier = Modifier) {
    Text(
        text = des,
        fontSize = 16.sp
    )
}

@Composable
fun CompletedText(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        fontWeight = FontWeight.Bold,
        modifier = modifier.padding(top = 24.dp, bottom = 8.dp)
    )
}

@Composable
fun CompletedImage(modifier: Modifier = Modifier) {
    val image = painterResource(id = R.drawable.ic_task_completed)
    Image(painter = image, contentDescription = "Icon completed", modifier = modifier)
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun UIPreview() {
    AndroidBasicComposeTheme {
        TaskManager(
            stringResource(R.string.all_tasks_completed),
            stringResource(R.string.nice_work)
        )
    }
}