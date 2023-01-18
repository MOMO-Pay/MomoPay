package com.hover.stax.ui.views.money.send.state

import com.hover.stax.ui.views.money.data.MoMo

sealed class State {
    object Idle : State()
    object PaymentMethod : State()
    data class Transacting(
        val moMo: MoMo
    ) : State()
    object Completed : State()
    object Failed : State()
}