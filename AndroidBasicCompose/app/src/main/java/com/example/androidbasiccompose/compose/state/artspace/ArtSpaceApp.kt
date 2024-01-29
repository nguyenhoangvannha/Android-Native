package com.example.androidbasiccompose.compose.state.artspace

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androidbasiccompose.R
import com.example.androidbasiccompose.ui.theme.AndroidBasicComposeTheme

@Composable
fun ArtSpaceApp(modifier: Modifier = Modifier) {
    val total = 5

    val images = List(total) { R.drawable.androidparty }


    var currentIndex = remember {
        mutableStateOf(0)
    }

    val image = images[currentIndex.value]

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = modifier.padding(16.dp)) {
        ArtFrame(
            image = painterResource(image),
            modifier = Modifier.weight(weight = 0.5F)
        )
        ArtDescription(
            title = R.string.a_layout_composable_that_places_its_children_in_a_horizontal_sequence,
            owner = R.string.row_composable, publicationYear = 2021 + currentIndex.value,
        )
        ArtNavigation(
            onNext = {
                currentIndex.value = when {
                    (currentIndex.value < total - 1) -> currentIndex.value + 1
                    else -> 0
                }
            },
            onPrevious = {
                currentIndex.value = when {
                    (currentIndex.value > 0) -> currentIndex.value - 1
                    else -> total - 1
                }
            },
        )
    }
}

@Composable
fun ArtNavigation(
    onNext: () -> Unit,
    onPrevious: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row {
        NavButton(R.string.previous, onClicked = onPrevious)
        NavButton(R.string.next, onClicked = onNext)
    }
}

@Composable
fun NavButton(@StringRes text: Int, onClicked: () -> Unit) {
    Button(onClick = onClicked) {
        Text(stringResource(id = text))
    }
}

@Composable
fun ArtDescription(
    @StringRes title: Int,
    @StringRes owner: Int,
    publicationYear: Int,
    modifier: Modifier = Modifier
) {
    Column {
        Text(text = stringResource(id = title))
        Row {
            Text(text = stringResource(id = owner))
            Text(text = stringResource(id = R.string.publicationYear, publicationYear))
        }
    }

}

@Composable
fun ArtFrame(
    image: Painter,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.padding(16.dp),
        shadowElevation = 18.dp,
    ) {
        Image(painter = image, contentDescription = null)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ArtSpacePreview() {
    AndroidBasicComposeTheme {
        ArtSpaceApp()
    }
}