package com.example.androidbasiccompose

import com.example.androidbasiccompose.compose.state.tipcalculator.calculateTip
import org.junit.Assert.assertEquals
import org.junit.Test
import java.text.NumberFormat

class TipCalculatorTests {
    @Test
    fun calculateTip_20PercentNoRoundUp() {
        //Given
        val amount = 10.00
        val tipPercent = 20.00
        val expectedTip = NumberFormat.getCurrencyInstance().format(2)
        //When
        val actualTip = calculateTip(amount, tipPercent, false)
        //Then
        assertEquals(expectedTip, actualTip)
    }
}