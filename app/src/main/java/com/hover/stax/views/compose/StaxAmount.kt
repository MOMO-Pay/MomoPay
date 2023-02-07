package com.hover.stax.views.compose

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.hover.stax.ui.theme.StaxTheme

@Composable
fun StaxAmount(
    modifier: Modifier = Modifier,
    amount: String,
) {

    Text(
        text = amount,
        modifier = modifier.fillMaxWidth(),
        fontSize = 32.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center
    )
}

@Preview
@Composable
fun StaxAmountPreview() {
    StaxTheme {
        StaxAmount(amount = "100")
    }
}