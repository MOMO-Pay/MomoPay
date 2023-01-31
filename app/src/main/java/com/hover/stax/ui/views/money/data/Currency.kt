package com.hover.stax.ui.views.money.data

data class Currency(
    val code: String,
    val symbol: String
) {
    companion object {

        private val KES = Currency(code = "KES", symbol = "KES")

        val list = listOf(KES)

        val default = KES

        private val map = list.associateBy { it.code }

        fun getByCode(name: String): Currency? {
            return map[name]
        }
    }
}