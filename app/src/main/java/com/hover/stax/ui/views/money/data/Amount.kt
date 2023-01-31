package com.hover.stax.ui.views.money.data

data class Amount(
    val currency: Currency,
    val amountValue: Double
) {

    @Throws(IllegalStateException::class)
    fun convertToCurrency(
        toCurrency: Currency
    ): Double = amountValue


    companion object {

        val default = Amount(
            currency = Currency.default,
            amountValue = 0.0
        )
    }
}