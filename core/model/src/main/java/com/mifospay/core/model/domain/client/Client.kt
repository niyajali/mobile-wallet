/*
 * Copyright 2024 Mifos Initiative
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 * See https://github.com/openMF/mobile-wallet/blob/master/LICENSE.md
 */
package com.mifospay.core.model.domain.client

import kotlinx.serialization.Serializable

@Serializable
data class Client(
    var name: String? = null,
    var image: String,
    var externalId: String? = null,
    var clientId: Long = 0L,
    var displayName: String,
    var mobileNo: String,
) {
    constructor() : this("", "", "", 0L, "", "")
}
