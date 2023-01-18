package com.hover.stax.ui.views.money.send.state

sealed class Event {
    object ChoosePaymentMethod : Event()
    object SendMoney : Event()
}