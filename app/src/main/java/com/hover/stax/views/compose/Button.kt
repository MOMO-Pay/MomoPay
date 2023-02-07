package com.hover.stax.views.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ChevronLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun StaxBackButton(
    onClick: () -> Unit,
    imageVector: ImageVector = Icons.Rounded.ChevronLeft
) {
    StaxIconButton(
        onClick = onClick,
        modifier = Modifier.size(28.dp)
    ) {
        StaxIcon(
            imageVector = imageVector,
        )
    }
}

@Composable
fun StaxIconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    color: Color = MaterialTheme.colors.secondary,
    content: @Composable () -> Unit
) {
    val shape = CircleShape
    IconButton(
        onClick = onClick,
        modifier = modifier
            .background(
                color = color,
                shape = shape
            )
            .clip(shape),
        enabled = enabled
    ) {
        content()
    }
}

@Preview
@Composable
fun StaxBackButton() {
    StaxBackButton(onClick = {})
}