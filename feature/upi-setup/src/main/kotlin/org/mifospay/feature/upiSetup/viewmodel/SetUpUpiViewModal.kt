/*
 * Copyright 2024 Mifos Initiative
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 * See https://github.com/openMF/mobile-wallet/blob/master/LICENSE.md
 */
package org.mifospay.feature.upiSetup.viewmodel

import androidx.lifecycle.ViewModel
import com.mifospay.core.model.domain.BankAccountDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
@Suppress("UnusedParameter")
class SetUpUpiViewModal @Inject constructor() : ViewModel() {

    fun requestOtp(bankAccountDetails: BankAccountDetails?): String {
        val otp = "0000"
        return otp
    }

    fun setupUpiPin(bankAccountDetails: BankAccountDetails?, mSetupUpiPin: String?) {
        // to do setup upi pin api
    }
}
