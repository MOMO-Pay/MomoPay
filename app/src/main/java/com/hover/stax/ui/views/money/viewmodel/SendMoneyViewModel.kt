package com.hover.stax.ui.views.money.viewmodel

import androidx.lifecycle.ViewModel
import com.hover.stax.channels.Channel
import com.hover.stax.domain.model.Account
import com.hover.stax.ui.views.money.data.SendMoneyParams
import com.hover.stax.utils.toString
import com.hover.stax.views.compose.KeyboardCommand
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SendMoneyViewModel : ViewModel() {

    private val _account = MutableStateFlow(Account(Channel(), true, 1))
    val account: StateFlow<Account> = _account.asStateFlow()

    private val initialAmount = MutableStateFlow(SendMoneyParams())
    private val _amountText: MutableStateFlow<String> = MutableStateFlow(
        initialAmount.value.amount.amountValue.toString(precision = 2)
    )
    val amountText: StateFlow<String> = _amountText.asStateFlow()

    fun onKeyboardButtonClick(command: KeyboardCommand) {
        val amountText = _amountText.value
        var newAmountText = amountText
        when (command) {
            KeyboardCommand.Delete -> {
                when {
                    amountText.length <= 1 && amountText.toDouble() == 0.0 -> {
                        /* ignore */
                    }

                    amountText.length <= 1 && amountText.toDouble() != 0.0 -> {
                        newAmountText = "0"
                    }

                    else -> {
                        newAmountText = newAmountText.dropLast(1)
                    }
                }
            }

            is KeyboardCommand.Digit -> {
                if (amountText == "0") {
                    newAmountText = command.value.toString()
                } else {
                    newAmountText += command.value.toString()
                }
            }

            KeyboardCommand.Point -> {
                if (!newAmountText.contains(".")) {
                    newAmountText += "."
                }
            }
        }
        if (newAmountText.matches(Regex("^\\d*\\.?\\d*"))) {
            _amountText.value = newAmountText
        }
    }
}