package com.example.androidbasiccompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.androidbasiccompose.birthday.BirthDayCard
import com.example.androidbasiccompose.compose.diceroller.DiceRollerApp
import com.example.androidbasiccompose.compose.practice.ComposeQuadrant
import com.example.androidbasiccompose.compose.state.tipcalculator.TipTimeLayout
import com.example.androidbasiccompose.ui.theme.AndroidBasicComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidBasicComposeTheme {
                TipTimeLayout()
            }
        }
    }
}
