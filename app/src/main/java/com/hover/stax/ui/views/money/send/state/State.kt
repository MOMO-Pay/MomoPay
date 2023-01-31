package com.hover.stax.ui.views.money.send.state


sealed class State {
    object Idle : State()
    object PaymentMethod : State()
    data class Transacting(
        val moMo: String
    ) : State()
    object Completed : State()
    object Failed : State()
}