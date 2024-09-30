/*
 * Copyright 2024 Mifos Initiative
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 *
 * See https://github.com/openMF/mobile-wallet/blob/master/LICENSE.md
 */
package org.mifospay.core.common

import platform.Foundation.NSNumberFormatter

actual object CurrencyFormatter {
    actual fun format(
        balance: Double?,
        currencyCode: String?,
        maximumFractionDigits: Int?,
    ): String {
        val numberFormatter = NSNumberFormatter()
        numberFormatter.numberStyle = NSNumberFormatterCurrencyStyle
        numberFormatter.currencyCode = currencyCode
        numberFormatter.maximumFractionDigits = maximumFractionDigits ?: 0
        return numberFormatter.stringFromNumber(balance ?: 0.0) ?: ""
    }
}
