package com.hover.stax.ui.views.money.send

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.hover.stax.R
import com.hover.stax.presentation.home.components.TopBar
import com.hover.stax.ui.theme.StaxTheme
import com.hover.stax.views.compose.NumKeypad

@Composable
fun SendMoney(
    navTo: (dest: Int) -> Unit
) {
    StaxTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
            Scaffold(
                topBar = { TopBar(title = R.string.nav_send_money, navTo) },
                content = { padding ->
                    NumKeypad(
                        callback = {

                        },
                        modifier = Modifier.padding(padding)
                    )
                    Row(modifier = Modifier.padding(padding)) {
                        Button(
                            onClick = {}, modifier = Modifier
                                .weight(1f)
                                .padding(padding)
                        ) {
                            Text(
                                text = stringResource(id = R.string.payment_type_screen_cancel),
                                color = Color.White,
                                fontSize = 28.sp
                            )
                        }
                        Button(
                            onClick = {}, modifier = Modifier
                                .weight(1f)
                                .padding(padding)
                        ) {
                            Text(
                                text = stringResource(id = R.string.payment_type_screen_next),
                                color = Color.White,
                                fontSize = 28.sp
                            )
                        }
                    }
                }
            )
        }
    }
}

@Preview
@Composable
fun SendMoneyPreview() {
    SendMoney {}
}