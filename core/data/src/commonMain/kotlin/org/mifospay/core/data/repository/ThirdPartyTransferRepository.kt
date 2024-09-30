/*
 * Copyright 2024 Mifos Initiative
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 * See https://github.com/openMF/mobile-wallet/blob/master/LICENSE.md
 */
package org.mifospay.core.data.repository

import kotlinx.coroutines.flow.Flow
import org.mifospay.core.common.Result
import org.mifospay.core.model.entity.TPTResponse
import org.mifospay.core.model.entity.payload.TransferPayload
import org.mifospay.core.model.entity.templates.account.AccountOptionsTemplate

interface ThirdPartyTransferRepository {
    suspend fun getTransferTemplate(): Flow<Result<AccountOptionsTemplate>>

    suspend fun makeTransfer(payload: TransferPayload): Flow<Result<TPTResponse>>
}
