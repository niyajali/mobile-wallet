/*
 * Copyright 2024 Mifos Initiative
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 * See https://github.com/openMF/mobile-wallet/blob/master/LICENSE.md
 */
package org.mifospay.feature.faq.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import org.mifospay.feature.faq.FaqScreenRoute

const val FAQ_ROUTE = "faq_route"

fun NavController.navigateToFAQ(navOptions: NavOptions? = null) {
    this.navigate(FAQ_ROUTE, navOptions)
}

fun NavGraphBuilder.faqScreen(
    navigateBack: () -> Unit,
) {
    composable(route = FAQ_ROUTE) {
        FaqScreenRoute(
            navigateBack = navigateBack,
        )
    }
}
