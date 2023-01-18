package com.hover.stax.views.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType.Companion.LongPress
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hover.stax.ui.theme.StaxTheme

@Composable
fun NumKeypad(modifier: Modifier = Modifier, callback: (text: String) -> Any) {
    Column(
        modifier = modifier
            .padding(16.dp)
    ) {
        NumKeypadRow(
            listOf("1", "2", "3"),
            listOf(1f, 1f, 1f),
            callback
        )
        NumKeypadRow(
            listOf("4", "5", "6"),
            listOf(1f, 1f, 1f),
            callback
        )
        NumKeypadRow(
            listOf("7", "8", "9"),
            listOf(1f, 1f, 1f),
            callback
        )
        NumKeypadRow(
            listOf("", "0", ""),
            listOf(1f, 1f, 1f),
            callback
        )
    }
}

@Composable
fun NumKeypadRow(
    texts: List<String>,
    weights: List<Float>,
    callback: (text: String) -> Any
) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        for (index in texts.indices) {
            NumPadButton(
                text = texts[index],
                modifier = Modifier.weight(weights[index]),
                callback = callback
            )
        }
    }
}

@Composable
fun NumPadButton(
    text: String,
    callback: (text: String) -> Any,
    modifier: Modifier = Modifier
) {
    val hapticFeedback = LocalHapticFeedback.current
    TextButton(
        modifier = modifier
            .padding(8.dp),
        onClick = {
            hapticFeedback.performHapticFeedback(hapticFeedbackType = LongPress)
            callback(text)
        }
    ) {
        Text(text = text, color = Color.White, fontSize = 28.sp)
    }
}

@Preview
@Composable
fun NumKeypadPreview() {
    StaxTheme {
        NumKeypad(callback = {})
    }
}