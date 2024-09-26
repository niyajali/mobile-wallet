/*
 * Copyright 2024 Mifos Initiative
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 * See https://github.com/openMF/mobile-wallet/blob/master/LICENSE.md
 */
package org.mifospay.feature.upiSetup.navigation

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.mifospay.core.model.domain.BankAccountDetails
import org.mifospay.core.common.Constants
import org.mifospay.feature.upiSetup.screens.SetupUpiPinScreenRoute

const val SETUP_UPI_PIN_ROUTE = "setup_upi_pin_route"

fun NavGraphBuilder.setupUpiPinScreen(
    onBackPress: () -> Unit,
) {
    composable(
        route = "$SETUP_UPI_PIN_ROUTE/{${Constants.INDEX}}/{${Constants.TYPE}}",
        arguments = listOf(
            navArgument(Constants.INDEX) { type = NavType.IntType },
            navArgument(Constants.TYPE) { type = NavType.StringType },
        ),
    ) { backStackEntry ->
        val bankAccountDetails =
            backStackEntry.arguments?.getParcelable(Constants.BANK_ACCOUNT_DETAILS)
                ?: BankAccountDetails("", "", "", "", "")
        val index = backStackEntry.arguments?.getInt(Constants.INDEX) ?: 0
        val type = backStackEntry.arguments?.getString(Constants.TYPE) ?: ""

        SetupUpiPinScreenRoute(
            type = type,
            index = index,
            bankAccountDetails = bankAccountDetails,
            onBackPress = onBackPress,
        )
    }
}

fun NavController.navigateToSetupUpiPin(
    bankAccountDetails: BankAccountDetails,
    index: Int,
    type: String,
) {
    val bundle = Bundle().apply {
        putParcelable(Constants.BANK_ACCOUNT_DETAILS, bankAccountDetails)
    }
    this.navigate("$SETUP_UPI_PIN_ROUTE/$index/$type") {
        this.launchSingleTop = true
        this.restoreState = true
    }
    currentBackStackEntry?.arguments?.putAll(bundle)
}
