package com.example.androidbasiccompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.androidbasiccompose.birthday.BirthDayCard
import com.example.androidbasiccompose.compose.practice.ComposeQuadrant
import com.example.androidbasiccompose.ui.theme.AndroidBasicComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidBasicComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ComposeQuadrant(
                        titleList = listOf(
                            "Text composable",
                            "Image composable",
                            "Row composable",
                            "Column composable"
                        ),
                        desList = listOf(
                            "Displays text and follows the recommended Material Design guidelines.",
                            "Creates a composable that lays out and draws a given Painter class object.",
                            "A layout composable that places its children in a horizontal sequence.",
                            "A layout composable that places its children in a vertical sequence.",
                        )
                    )
                }
            }
        }
    }
}
