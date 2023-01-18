package com.hover.stax.ui.views.money.send.state

import com.tinder.StateMachine

fun create(): StateMachine<State, Event, SideEffect> {

    return StateMachine.create {

        initialState(State.Idle)

        state<State.Idle> {
            on<Event.ChoosePaymentMethod> {
                transitionTo(State.PaymentMethod, SideEffect.OpenChoosePaymentMethod)
            }
        }

        state<State.PaymentMethod> {
            on<Event.SendMoney> {
                transitionTo(State.Completed, SideEffect.ShowSendMoney)
            }
        }

        onTransition {
            val validTransition = (it as? StateMachine.Transition.Valid) ?: return@onTransition
            when (validTransition.sideEffect) {
                SideEffect.OpenChoosePaymentMethod -> {
                    // do something
                }
                else -> {
                    // do something else
                }
            }
        }
    }
}