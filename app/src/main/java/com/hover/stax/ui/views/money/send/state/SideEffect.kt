package com.hover.stax.ui.views.money.send.state

sealed class SideEffect {
    object OpenChoosePaymentMethod : SideEffect()
    object ShowSendMoney : SideEffect()
}