package com.hover.stax.navigation

sealed class Flow(val name: String) {

    object Root : Flow("payment-type-root") {
        val route = name
    }

    object PaymentTypeScreenFlow : Flow("payment-type-screen") {
        val route = name
    }
}