/*
 * Copyright 2024 Mifos Initiative
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 * See https://github.com/openMF/mobile-wallet/blob/master/LICENSE.md
 */
package org.mifospay.core.network.model.entity.accounts.savings

import kotlinx.serialization.Serializable

@Serializable
data class BlockUnblockResponseEntity(
    val officeId: Long,
    val clientId: Long,
    val savingsId: Long,
    val resourceId: Long,
    val changes: ChangesEntity,
)

@Serializable
data class ChangesEntity(
    val subStatus: SubStatus,
)
