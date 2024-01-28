package com.example.androidbasiccompose.compose.practice

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
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
fun ComposeArticle(header: String, prgh1: String, prgh2: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        TopImage()
        HeaderText(header)
        Paragraph1(prgh1)
        Paragraph2(prgh2)
    }
}

@Composable
fun Paragraph2(prgh2: String, modifier: Modifier = Modifier) {
    Text(
        text = prgh2,
        modifier = modifier.padding(16.dp),
        textAlign = TextAlign.Justify
    )
}

@Composable
fun Paragraph1(prgh1: String, modifier: Modifier = Modifier) {
    Text(
        text = prgh1,
        modifier = modifier.padding(horizontal = 16.dp),
        textAlign = TextAlign.Justify
    )
}

@Composable
fun HeaderText(headerT: String, modifier: Modifier = Modifier) {
    Text(
        text = headerT, modifier = modifier.padding(16.dp), fontSize = 24.sp,
    )
}

@Composable
fun TopImage(modifier: Modifier = Modifier) {
    val image = painterResource(id = R.drawable.bg_compose_background)
    Image(
        painter = image,
        contentDescription = null,
        contentScale = ContentScale.FillWidth,
        modifier = modifier
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun BirthdayCardPreview() {
    AndroidBasicComposeTheme {
        ComposeArticle(
            stringResource(R.string.jetpack_compose_tutorial),
            stringResource(R.string.p1),
            stringResource(R.string.p2)
        )
    }
}