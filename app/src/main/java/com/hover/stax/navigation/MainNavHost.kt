package com.hover.stax.navigation

import androidx.compose.material.ModalBottomSheetDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.ModalBottomSheetLayout
import com.google.accompanist.navigation.material.bottomSheet
import com.google.accompanist.navigation.material.rememberBottomSheetNavigator
import com.hover.stax.ui.views.money.choose.PaymentTypeScreen
import com.hover.stax.ui.views.money.send.SendMoney

@OptIn(ExperimentalMaterialNavigationApi::class)
@Composable
fun MainNavHost() {

    val bottomSheetNavigator = rememberBottomSheetNavigator()
    val bottomSheetConfig = remember { mutableStateOf(DefaultBottomSheetConfig) }
    val navController = rememberNavController(bottomSheetNavigator)

    ModalBottomSheetLayout(
        bottomSheetNavigator = bottomSheetNavigator,
        sheetShape = bottomSheetConfig.value.sheetShape,
        scrimColor = if (bottomSheetConfig.value.showScrim) {
            ModalBottomSheetDefaults.scrimColor
        } else {
            Color.Transparent
        }
    ) {
        NavHost(
            navController = navController,
            startDestination = PaymentScreens.SendMoney.route
        ) {

            composable(route = PaymentScreens.SendMoney.route) {
                SendMoney(navTo = { navigate(PaymentScreens.PaymentTypeScreen.route, navController) })
            }

            bottomSheet(PaymentScreens.PaymentTypeScreen.route) {
                bottomSheetConfig.value = DefaultBottomSheetConfig
                PaymentTypeScreen(
                    onClickBack = { navController.navigateUp() }
                )
            }
        }
    }
}

fun navigate(route: String, navController: NavController) {
    navController.navigate(route)
}

fun navigateWithPop(route: String, navController: NavController) {
    navController.navigate(route) {
        popUpTo(PaymentScreens.SendMoney.route) { inclusive = true }
    }
}