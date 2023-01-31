package com.hover.stax.ui.views.money.data

import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.text.intl.Locale
import java.text.NumberFormat
import java.util.Currency
import kotlin.math.pow
import java.util.Locale as JavaLocale

@Composable
fun formatAmount(
    number: Double,
    currencyCode: String,
    reductionMode: ReductionMode
): String {
    val locale = Locale.current
    val amountFormatter by remember(locale) {
        derivedStateOf { AmountFormat(locale.toJavaLocale()) }
    }
    return amountFormatter.format(number, currencyCode, reductionMode)
}

class AmountFormat(locale: JavaLocale) {

    private val formatter = NumberFormat.getCurrencyInstance(locale).apply {
        maximumFractionDigits = 2
        minimumFractionDigits = 0
    }

    fun format(
        number: Double,
        currencyCode: String,
        reductionMode: ReductionMode
    ): String {

        formatter.currency = Currency.getInstance(currencyCode)

        var shortNumber = number
        var suffix = ""

        when {
            number >= Billion -> {
                while (shortNumber >= Billion) {
                    shortNumber /= Billion
                    suffix += "B"
                }
            }
            number >= Million -> {
                shortNumber = number / Million
                suffix = "M"
            }
            reductionMode == ReductionMode.Hard && number >= Thousand -> {
                shortNumber = number / Thousand
                suffix = "k"
            }
        }

        val formattedNumber = formatter.format(shortNumber.round(formatter.maximumFractionDigits))
        val formattedNumberWithoutSigns = formattedNumber
            .replace("-", "")
            .replace("+", "")
            .replace(" ", "")

        return if (formattedNumberWithoutSigns.last().isDigit()) {
            formattedNumber + suffix
        } else {
            val lastDigitIndex = formattedNumber.indexOfLast { it.isDigit() }
            val numberString = formattedNumber.substring(0, lastDigitIndex + 1)
            val currencyString = formattedNumber.substring(lastDigitIndex + 1)
            numberString + suffix + currencyString
        }
    }

    private fun Double.round(decimals: Int): Double {
        val multiplier = 10.0.pow(decimals)
        return (this * multiplier).toInt() / multiplier
    }

    companion object {
        private const val Billion = 1_000_000_000
        private const val Million = 1_000_000
        private const val Thousand = 1_000
    }
}

fun Locale.toJavaLocale(): JavaLocale {
    return JavaLocale.forLanguageTag(toLanguageTag())
}