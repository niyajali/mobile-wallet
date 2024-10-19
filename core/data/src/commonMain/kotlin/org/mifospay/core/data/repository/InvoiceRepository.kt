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
import org.mifospay.core.common.DataState
import org.mifospay.core.model.datatables.invoice.Invoice
import org.mifospay.core.model.datatables.invoice.InvoiceEntity

interface InvoiceRepository {
    fun getInvoice(clientId: Long, invoiceId: Long): Flow<DataState<Invoice>>

    fun getInvoices(clientId: Long): Flow<DataState<List<Invoice>>>

    suspend fun createInvoice(clientId: Long, invoice: InvoiceEntity): DataState<String>

    suspend fun updateInvoice(
        clientId: Long,
        invoiceId: Long,
        invoice: InvoiceEntity,
    ): DataState<String>

    suspend fun deleteInvoice(clientId: Long, invoiceId: Long): DataState<String>
}
