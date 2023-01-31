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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.hover.stax.R
import com.hover.stax.ui.theme.StaxTheme
import com.hover.stax.ui.views.money.data.Currency
import com.hover.stax.ui.views.money.viewmodel.SendMoneyViewModel
import com.hover.stax.views.compose.AmountKeyboard
import com.hover.stax.views.compose.AmountTextField
import com.hover.stax.views.compose.NavTopBar
import com.hover.stax.views.compose.SendMoneyOption
import org.koin.androidx.compose.koinViewModel

@Composable
fun SendMoney(
    navTo: (dest: Int) -> Unit,
    viewModel: SendMoneyViewModel = koinViewModel()
) {
    val amountText by viewModel.amountText.collectAsState()
    val account by viewModel.account.collectAsState()

    StaxTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
            Scaffold(
                topBar = { NavTopBar(title = R.string.nav_send_money, navTo) }
            ) { padding ->
                ConstraintLayout(
                    modifier = Modifier
                        .padding(padding)
                        .fillMaxSize()
                ) {
                    val (amountField, accountField, numKeypad, buttons) = createRefs()

                    AmountTextField(modifier = Modifier.constrainAs(amountField) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        height = Dimension.fillToConstraints
                    }, currency = Currency.default, amount = amountText)

                    SendMoneyOption(modifier = Modifier.constrainAs(accountField) {
                        top.linkTo(amountField.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        height = Dimension.fillToConstraints
                    }, account = account, navTo = {})

                    AmountKeyboard(
                        modifier = Modifier.constrainAs(numKeypad) {
                            top.linkTo(accountField.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            bottom.linkTo(buttons.top)
                            height = Dimension.fillToConstraints
                        }
                    ) {
                        viewModel.onKeyboardButtonClick(it)
                    }

                    Row(modifier = Modifier.constrainAs(buttons) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    }) {
                        Button(
                            onClick = {

                            }, modifier = Modifier
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
        }
    }
}

@Preview
@Composable
fun SendMoneyPreview() {
//    SendMoney {}
}