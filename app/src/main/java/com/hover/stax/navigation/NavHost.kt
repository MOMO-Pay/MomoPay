package com.hover.stax.navigation

import androidx.compose.runtime.MutableState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.bottomSheet
import com.hover.stax.ui.views.money.choose.PaymentTypeScreen

@OptIn(ExperimentalMaterialNavigationApi::class)
fun NavGraphBuilder.paymentTypeNavHost(
    navController: NavHostController,
    bottomSheetConfig: MutableState<BottomSheetConfig>
){
    navigation(startDestination = Flow.PaymentTypeScreenFlow.route, route = Flow.Root.route) {
        bottomSheet(Flow.PaymentTypeScreenFlow.route) {
            bottomSheetConfig.value = DefaultBottomSheetConfig
            PaymentTypeScreen(
                onClickBack = { navController.navigateUp() }
            )
        }
    }
}