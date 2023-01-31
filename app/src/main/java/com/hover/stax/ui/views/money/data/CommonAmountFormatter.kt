package com.hover.stax.ui.views.money.data

import androidx.compose.runtime.Composable
import com.hover.stax.utils.negativeSignOrEmpty
import kotlin.math.absoluteValue

enum class AmountFormatMode {
    NegativeSign,
    NoSigns
}

enum class ReductionMode {
    Soft,
    Hard
}

@Composable
internal fun Amount.format(
    mode: AmountFormatMode = AmountFormatMode.NoSigns,
    reductionMode: ReductionMode = ReductionMode.Soft
): String {
    val formattedCurrencyNumber = formatAmount(
        number = amountValue.absoluteValue,
        currencyCode = currency.code,
        reductionMode = reductionMode
    ).replace(currency.code, currency.symbol)

    return when (mode) {
        AmountFormatMode.NegativeSign -> {
            amountValue.negativeSignOrEmpty + formattedCurrencyNumber
        }
        AmountFormatMode.NoSigns -> {
            formattedCurrencyNumber
        }
    }
}