package com.hover.stax.ui.views.money.data

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import com.hover.stax.R

@Immutable
data class PaymentTypeState(
    val items: List<PaymentTypeItem> = listOf()
)

enum class MoMo(@StringRes val label: Int) {
    MPESA(R.string.momo_mpesa)
}

data class PaymentTypeItem(
    val momo: MoMo,
    val applied: Boolean
)

fun List<PaymentTypeItem>.select(momo: MoMo): List<PaymentTypeItem> {
    return map {
        it.copy(applied = it.momo == momo)
    }
}