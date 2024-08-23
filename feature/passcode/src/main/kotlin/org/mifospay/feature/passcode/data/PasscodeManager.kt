/*
 * Copyright 2024 Mifos Initiative
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 * See https://github.com/openMF/mobile-wallet/blob/master/LICENSE.md
 */
package org.mifospay.feature.passcode.data

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import org.mifospay.feature.passcode.utility.PreferenceManager
import javax.inject.Inject

class PasscodeManager @Inject constructor(
    @ApplicationContext
    private val context: Context,
) {
    private val passcodePreferencesHelper = PreferenceManager(context)

    val getPasscode = passcodePreferencesHelper.getSavedPasscode()

    val hasPasscode = passcodePreferencesHelper.hasPasscode

    fun savePasscode(passcode: String) {
        passcodePreferencesHelper.savePasscode(passcode)
    }

    fun clearPasscode() {
        passcodePreferencesHelper.clearPasscode()
    }
}
