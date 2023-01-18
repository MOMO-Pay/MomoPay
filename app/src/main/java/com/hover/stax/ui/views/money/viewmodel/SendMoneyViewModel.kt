package com.hover.stax.ui.views.money.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hover.stax.ui.views.money.data.MoMo
import com.hover.stax.ui.views.money.data.PaymentTypeItem
import com.hover.stax.ui.views.money.data.PaymentTypeState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SendMoneyViewModel : ViewModel() {

    private val _state = MutableStateFlow(PaymentTypeState())
    val state = _state.asStateFlow()

    init {
        initPaymentTypes()
    }

    private fun initPaymentTypes() = viewModelScope.launch {
        _state.update { it.copy(items = initial()) }
    }

    private fun initial(): List<PaymentTypeItem> {
        val data = mutableListOf<PaymentTypeItem>()
        data.add(
            PaymentTypeItem(
                momo = MoMo.MPESA,
                applied = false
            )
        )
        return data
    }
}