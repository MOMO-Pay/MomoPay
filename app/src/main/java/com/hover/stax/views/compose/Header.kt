package com.hover.stax.views.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun StaxHeader(
    text: String,
    onClickBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        Box(
            modifier = Modifier
                .padding(start = 16.dp)
                .weight(0.2F)
        ) {
            StaxBackButton(
                onClick = onClickBack
            )
        }

        StaxTitle(
            text = text,
            modifier = Modifier.weight(0.6F)
        )

        Spacer(
            Modifier
                .size(0.dp)
                .weight(0.2F)
        )
    }
}