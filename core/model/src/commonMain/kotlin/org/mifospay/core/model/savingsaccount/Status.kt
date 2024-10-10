/*
 * Copyright 2024 Mifos Initiative
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 * See https://github.com/openMF/mobile-wallet/blob/master/LICENSE.md
 */
package org.mifospay.core.model.savingsaccount

import org.mifospay.core.common.Parcelable
import org.mifospay.core.common.Parcelize

@Parcelize
data class Status(
    val id: Int?,
    val code: String?,
    val value: String?,
    val submittedAndPendingApproval: Boolean?,
    val approved: Boolean?,
    val rejected: Boolean?,
    val withdrawnByApplicant: Boolean?,
    val active: Boolean?,
    val closed: Boolean?,
    val prematureClosed: Boolean?,
    val transferInProgress: Boolean?,
    val transferOnHold: Boolean?,
    val matured: Boolean?,
) : Parcelable
