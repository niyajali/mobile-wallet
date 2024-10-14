/*
 * Copyright 2024 Mifos Initiative
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 * See https://github.com/openMF/mobile-wallet/blob/master/LICENSE.md
 */
@file:Suppress("MaxLineLength")

package org.mifospay.feature.history.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import org.mifospay.feature.history.transactions.SpecificTransactionsScreen

const val SPECIFIC_TRANSACTIONS_BASE_ROUTE = "specific_transactions_route"
const val ACCOUNT_ARG = "accountId"
const val TRANSACTIONS_ARG = "transactionId"

const val SPECIFIC_TRANSACTIONS_ROUTE = SPECIFIC_TRANSACTIONS_BASE_ROUTE +
    "&$ACCOUNT_ARG={$ACCOUNT_ARG}" +
    "&$TRANSACTIONS_ARG={$TRANSACTIONS_ARG}"

fun NavGraphBuilder.specificTransactionsScreen(
    navigateBack: () -> Unit,
    viewTransactionDetail: (Long) -> Unit,
) {
    composable(
        route = SPECIFIC_TRANSACTIONS_ROUTE,
        arguments = listOf(
            navArgument(ACCOUNT_ARG) { type = NavType.LongType },
            navArgument(TRANSACTIONS_ARG) { type = NavType.LongType },
        ),
    ) {
        SpecificTransactionsScreen(
            navigateBack = navigateBack,
            viewTransaction = viewTransactionDetail,
        )
    }
}

fun NavController.navigateToSpecificTransaction(accountId: Long, transactionId: Long) {
    navigate(
        SPECIFIC_TRANSACTIONS_BASE_ROUTE +
            "&$ACCOUNT_ARG=$accountId&$TRANSACTIONS_ARG=$transactionId",
    )
}
