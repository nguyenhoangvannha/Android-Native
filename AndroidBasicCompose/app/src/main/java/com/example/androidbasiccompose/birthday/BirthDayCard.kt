package com.example.androidbasiccompose.birthday

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androidbasiccompose.ui.theme.AndroidBasicComposeTheme

@Composable
fun BirthDayCard(modifier: Modifier = Modifier) {
    GreetingText("Happy birthday Nha Nguyen", "From Buddha")
}

@Composable
private fun GreetingText(message: String, from: String, modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = modifier.padding(8.dp)
    ) {
        Text(
            text = "$message!",
            fontSize = 100.sp,
            lineHeight = 100.sp,
            textAlign = TextAlign.Center,
        )
        Text(
            text = from, fontSize = 36.sp,
            modifier = Modifier
                .padding(16.dp)
                .align(alignment = Alignment.End)
        )
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun BirthDayCardPreview() {
    AndroidBasicComposeTheme {
        BirthDayCard()
    }
}