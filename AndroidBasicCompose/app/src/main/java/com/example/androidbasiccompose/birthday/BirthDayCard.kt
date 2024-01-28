package com.example.androidbasiccompose.birthday

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androidbasiccompose.R
import com.example.androidbasiccompose.ui.theme.AndroidBasicComposeTheme

@Composable
fun BirthDayCard(modifier: Modifier = Modifier) {
    GreetingText(
        stringResource(R.string.happy_birthday_nha_nguyen),
        stringResource(R.string.signature_text)
    )
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
            modifier = Modifier.padding(8.dp)
        )
        Text(
            text = from, fontSize = 36.sp,
            modifier = Modifier
                .padding(16.dp)
                .align(alignment = Alignment.CenterHorizontally)
        )
    }
}

@Composable
fun GreetingImage(message: String, from: String, modifier: Modifier = Modifier) {
    val image = painterResource(id = R.drawable.androidparty)
    Box(modifier) {
        Image(
            painter = image, contentDescription = null, contentScale = ContentScale.Crop,
            alpha = 0.5F
        )
        GreetingText(
            message, from, modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun BirthdayCardPreview() {
    AndroidBasicComposeTheme {
        GreetingImage(
            stringResource(R.string.happy_birthday_nha_nguyen),
            stringResource(R.string.signature_text)
        )
    }
}