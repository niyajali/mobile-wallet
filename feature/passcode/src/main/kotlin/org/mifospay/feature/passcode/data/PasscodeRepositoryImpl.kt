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

import org.mifospay.feature.passcode.utility.PreferenceManager
import javax.inject.Inject

class PasscodeRepositoryImpl @Inject constructor(
    private val preferenceManager: PreferenceManager,
) : PasscodeRepository {

    override val hasPasscode: Boolean
        get() = preferenceManager.hasPasscode

    override fun getSavedPasscode(): String {
        return preferenceManager.getSavedPasscode()
    }

    override fun savePasscode(passcode: String) {
        preferenceManager.savePasscode(passcode)
    }
}
