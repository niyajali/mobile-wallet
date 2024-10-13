/*
 * Copyright 2024 Mifos Initiative
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 * See https://github.com/openMF/mobile-wallet/blob/master/LICENSE.md
 */
package org.mifospay.feature.profile.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import org.mifospay.feature.profile.edit.EditProfileScreen

const val EDIT_PROFILE_ROUTE = "edit_profile_route"
fun NavController.navigateToEditProfile(navOptions: NavOptions? = null) =
    navigate(EDIT_PROFILE_ROUTE, navOptions)

internal fun NavGraphBuilder.editProfileScreen(
    onBackPress: () -> Unit,
) {
    composable(route = EDIT_PROFILE_ROUTE) {
        EditProfileScreen(
            onBackClick = onBackPress,
        )
    }
}
