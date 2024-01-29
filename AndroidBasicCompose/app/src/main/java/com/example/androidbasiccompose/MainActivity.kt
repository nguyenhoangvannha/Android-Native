package com.example.androidbasiccompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.androidbasiccompose.compose.state.artspace.ArtSpaceApp
import com.example.androidbasiccompose.ui.theme.AndroidBasicComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidBasicComposeTheme {
                ArtSpaceApp()
            }
        }
    }
}
