/*
 * Copyright 2024 Mifos Initiative
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 * See https://github.com/openMF/mobile-wallet/blob/master/LICENSE.md
 */
package org.mifospay.core.model.entity.client

import kotlinx.serialization.Serializable
import org.mifospay.core.model.entity.accounts.savings.SavingAccount

@Serializable
data class ClientAccounts(
    var savingsAccounts: List<SavingAccount> = ArrayList(),
) {

    fun withSavingsAccounts(savingsAccounts: List<SavingAccount>): ClientAccounts {
        this.savingsAccounts = savingsAccounts
        return this
    }

    val recurringSavingsAccounts: List<SavingAccount?>
        get() = getSavingsAccounts(true)
    val nonRecurringSavingsAccounts: List<SavingAccount?>
        get() = getSavingsAccounts(false)

    private fun getSavingsAccounts(wantRecurring: Boolean): List<SavingAccount?> {
        val result: MutableList<SavingAccount?> = ArrayList()
        for (account in savingsAccounts) {
            if (account.isRecurring() == wantRecurring) {
                result.add(account)
            }
        }
        return result
    }
}
