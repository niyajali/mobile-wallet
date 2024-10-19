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

import de.jensklingenberg.ktorfit.http.Body
import de.jensklingenberg.ktorfit.http.DELETE
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.POST
import de.jensklingenberg.ktorfit.http.PUT
import de.jensklingenberg.ktorfit.http.Path
import kotlinx.coroutines.flow.Flow
import org.mifospay.core.model.datatables.invoice.Invoice
import org.mifospay.core.model.datatables.invoice.InvoiceEntity
import org.mifospay.core.network.utils.ApiEndPoints

interface InvoiceService {
    @GET(ApiEndPoints.DATATABLES + "/invoice/{clientId}")
    fun getInvoices(@Path("clientId") clientId: Long): Flow<List<Invoice>>

    @GET(ApiEndPoints.DATATABLES + "/invoice/{clientId}/{invoiceId}")
    fun getInvoice(
        @Path("clientId") clientId: Long,
        @Path("invoiceId") invoiceId: Long,
    ): Flow<List<Invoice>>

    @POST(ApiEndPoints.DATATABLES + "/invoice/{clientId}")
    suspend fun addInvoice(
        @Path("clientId") clientId: Long,
        @Body invoice: InvoiceEntity,
    ): Unit

    @PUT(ApiEndPoints.DATATABLES + "/invoice/{clientId}/{invoiceId}")
    suspend fun updateInvoice(
        @Path("clientId") clientId: Long,
        @Path("invoiceId") invoiceId: Long,
        @Body invoice: InvoiceEntity,
    ): Unit

    @DELETE(ApiEndPoints.DATATABLES + "/invoice/{clientId}/{invoiceId}")
    suspend fun deleteInvoice(
        @Path("clientId") clientId: Long,
        @Path("invoiceId") invoiceId: Long,
    ): Unit
}
