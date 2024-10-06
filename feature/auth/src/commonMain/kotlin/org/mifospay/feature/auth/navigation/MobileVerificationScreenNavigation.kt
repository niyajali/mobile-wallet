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

package org.mifospay.feature.auth.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.mifospay.feature.auth.mobileVerify.MobileVerificationScreen

const val MOBILE_VERIFICATION_ROUTE = "mobile_verification_route"

fun NavGraphBuilder.mobileVerificationScreen(
    onNavigateBack: () -> Unit,
    onOtpVerificationSuccess: (String) -> Unit,
) {
    composable(
        route = MOBILE_VERIFICATION_ROUTE,
    ) {
        MobileVerificationScreen(
            onNavigateBack = onNavigateBack,
            onOtpVerificationSuccess = onOtpVerificationSuccess,
        )
    }
}

fun NavController.navigateToMobileVerification() {
    this.navigate(MOBILE_VERIFICATION_ROUTE)
}
