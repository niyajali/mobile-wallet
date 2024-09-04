/*
 * Copyright 2024 Mifos Initiative
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 * See https://github.com/openMF/mobile-wallet/blob/master/LICENSE.md
 */
package com.mifospay.core.model.entity.client

import com.google.gson.annotations.SerializedName
import com.mifospay.core.model.entity.Timeline
import kotlinx.serialization.Serializable

@Serializable
data class Client(

    @SerializedName("id")
    var id: Int = 0,

    @SerializedName("accountNo")
    var accountNo: String? = null,

    @SerializedName("status")
    private var status: Status? = null,

    @SerializedName("active")
    private var active: Boolean? = null,

    @SerializedName("activationDate")
    var activationDate: List<Int?> = ArrayList(),

    @SerializedName("dobDate")
    var dobDate: List<Int?> = ArrayList(),

    @SerializedName("firstname")
    var firstname: String? = null,

    @SerializedName("middlename")
    var middlename: String? = null,

    @SerializedName("lastname")
    var lastname: String? = null,

    @SerializedName("displayName")
    var displayName: String? = null,

    @SerializedName("fullname")
    var fullname: String? = null,

    @SerializedName("officeId")
    var officeId: Int = 0,

    @SerializedName("officeName")
    var officeName: String? = null,

    @SerializedName("staffId")
    private var staffId: Int? = null,

    @SerializedName("staffName")
    private var staffName: String? = null,

    @SerializedName("timeline")
    private var timeline: Timeline? = null,

    @SerializedName("imageId")
    var imageId: Int = 0,

    @SerializedName("imagePresent")
    var isImagePresent: Boolean = false,

    @SerializedName("externalId")
    var externalId: String = "",

    @SerializedName("mobileNo")
    var mobileNo: String = "",
) {
    constructor() : this(
        0,
        "",
        Status(),
        false,
        ArrayList(),
        ArrayList(),
        "",
        "",
        "",
        "",
        "",
        0,
        "",
        0,
        "",
        Timeline(),
        0,
        false,
        "",
        "",
    )
}
