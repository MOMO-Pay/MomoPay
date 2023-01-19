package com.hover.stax.ui.views.money.send

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
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
                    ConstraintLayout(modifier = Modifier.padding(padding).fillMaxSize()) {
                        val (numKeypad, buttons) = createRefs()
                        NumKeypad(
                            modifier = Modifier.constrainAs(numKeypad) {
                                top.linkTo(parent.top)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                                height = Dimension.fillToConstraints
                            }, {}
//                            onNumberClick = { },
//                            onBackspaceClick = { },
//                            onClearClick = { }
                        )
                        Row(modifier = Modifier.constrainAs(buttons){
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            bottom.linkTo(parent.bottom)
                        }) {
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
                                onClick = {
                                    navTo(R.id.action_global_NavigationSettings)
                                }, modifier = Modifier
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