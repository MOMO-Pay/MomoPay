package com.hover.stax.views.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hover.stax.ui.theme.Typography
import com.hover.stax.ui.views.money.data.Currency
import com.hover.stax.ui.views.money.data.scaleClickAnimation

@Composable
fun AmountTextField(
    currency: Currency?,
    amount: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(Color.Unspecified)
            .padding(16.dp)
            .fillMaxWidth()
            .scaleClickAnimation(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val currencySymbol = currency?.symbol
        Text(
            text = amount,
            color = Color.White,
            fontSize = 32.sp
        )
        currencySymbol?.let { currency ->
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = currency,
                color = Color.White,
                style = Typography.subtitle2
            )
        }
    }
}