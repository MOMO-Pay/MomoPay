package com.hover.stax.views.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.hover.stax.R
import com.hover.stax.ui.theme.Typography

sealed interface KeyboardCommand {
    data class Digit(val value: Int) : KeyboardCommand
    object Point : KeyboardCommand
    object Delete : KeyboardCommand
}

private val commands = listOf(
    KeyboardCommand.Digit(1), KeyboardCommand.Digit(2), KeyboardCommand.Digit(3),
    KeyboardCommand.Digit(4), KeyboardCommand.Digit(5), KeyboardCommand.Digit(6),
    KeyboardCommand.Digit(7), KeyboardCommand.Digit(8), KeyboardCommand.Digit(9),
    KeyboardCommand.Point, KeyboardCommand.Digit(0), KeyboardCommand.Delete,
)

private const val KeyboardRowCount = 3

@Composable
fun AmountKeyboard(
    modifier: Modifier = Modifier,
    onButtonClick: (KeyboardCommand) -> Unit = {}
) {
    Column(
        modifier = modifier
    ) {
        val commandsRows = commands.chunked(KeyboardRowCount)
        for (commandRow in commandsRows) {
            Row(
                modifier = Modifier.weight(1f)
            ) {
                for (command in commandRow) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .clickable { onButtonClick.invoke(command) },
                        contentAlignment = Alignment.Center
                    ) {
                        when (command) {
                            KeyboardCommand.Delete -> {
                                Icon(
                                    modifier = Modifier.size(24.dp),
                                    painter = painterResource(id = R.drawable.ic_backspace),
                                    contentDescription = null
                                )
                            }
                            is KeyboardCommand.Digit -> {
                                Text(
                                    text = command.value.toString(),
                                    style = Typography.h1,
                                    textAlign = TextAlign.Center
                                )
                            }
                            KeyboardCommand.Point -> {
                                Text(
                                    text = ".",
                                    style = Typography.h1,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}