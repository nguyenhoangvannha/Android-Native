package com.example.androidbasiccompose.compose.practice

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androidbasiccompose.R
import com.example.androidbasiccompose.ui.theme.AndroidBasicComposeTheme

@Composable
fun ComposeQuadrant(
    modifier: Modifier = Modifier,
    titleList: List<String>,
    desList: List<String>,
) {
    Column(modifier = modifier) {
        Row(modifier = Modifier.weight(weight = 0.5F)) {
            Quadrant(
                titleList.first(),
                desList.first(),
                Color(0xFFEADDFF),
                modifier = Modifier.weight(weight = 0.5F)
            )
            Quadrant(
                titleList[1],
                desList[1],
                Color(0xFFD0BCFF),
                modifier = Modifier.weight(weight = 0.5F)
            )
        }
        Row(modifier = Modifier.weight(weight = 0.5F)) {
            Quadrant(
                titleList[2],
                desList[2],
                Color(0xFFB69DF8),
                modifier = Modifier.weight(weight = 0.5F)
            )
            Quadrant(
                titleList[3],
                desList[3],
                Color(0xFFF6EDFF),
                modifier = Modifier.weight(weight = 0.5F)
            )
        }
    }

}

@Composable
fun Quadrant(first: String, first1: String, color: Color, modifier: Modifier = Modifier) {
    Surface(color = color, modifier = modifier) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = first,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
            )
            Text(
                text = first1,
                textAlign = TextAlign.Justify,
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun UI2Preview() {

    AndroidBasicComposeTheme {
        ComposeQuadrant(
            titleList = listOf(
                stringResource(R.string.text_composable),
                stringResource(R.string.image_composable),
                stringResource(R.string.row_composable),
                stringResource(R.string.column_composable)
            ),
            desList = listOf(
                stringResource(R.string.displays_text_and_follows_the_recommended_material_design_guidelines),
                stringResource(R.string.creates_a_composable_that_lays_out_and_draws_a_given_painter_class_object),
                stringResource(R.string.a_layout_composable_that_places_its_children_in_a_horizontal_sequence),
                stringResource(R.string.a_layout_composable_that_places_its_children_in_a_vertical_sequence),
            )
        )
    }
}