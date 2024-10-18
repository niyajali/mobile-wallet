/*
 * Copyright 2024 Mifos Initiative
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 * See https://github.com/openMF/mobile-wallet/blob/master/LICENSE.md
 */
package org.mifospay.core.network.services

import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.Path
import kotlinx.coroutines.flow.Flow
import org.mifospay.core.model.savingsaccount.TransactionsEntity
import org.mifospay.core.model.savingsaccount.TransferDetail
import org.mifospay.core.network.utils.ApiEndPoints

interface AccountTransfersService {

    @GET(ApiEndPoints.SAVINGS_ACCOUNTS + "/{accountId}/transactions/{transactionId}")
    fun getTransaction(
        @Path("accountId") clientId: Long,
        @Path("transactionId") transactionId: Long,
    ): Flow<TransactionsEntity>

    @GET(ApiEndPoints.ACCOUNT_TRANSFER + "/{transferId}")
    fun getAccountTransfer(@Path("transferId") transferId: Int): Flow<TransferDetail>
}
