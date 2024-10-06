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
import org.mifospay.core.model.entity.savedcards.Card
import org.mifospay.core.network.ApiEndPoints
import org.mifospay.core.network.model.GenericResponse

interface SavedCardService {
    @POST(ApiEndPoints.DATATABLES + "/saved_cards/{clientId}")
    suspend fun addSavedCard(
        @Path("clientId") clientId: Int,
        @Body card: Card,
    ): Flow<GenericResponse>

    @GET(ApiEndPoints.DATATABLES + "/saved_cards/{clientId}")
    suspend fun getSavedCards(@Path("clientId") clientId: Int): Flow<List<Card>>

    @DELETE(ApiEndPoints.DATATABLES + "/saved_cards/{clientId}/{cardId}")
    suspend fun deleteCard(
        @Path("clientId") clientId: Int,
        @Path("cardId") cardId: Int,
    ): Flow<GenericResponse>

    @PUT(ApiEndPoints.DATATABLES + "/saved_cards/{clientId}/{cardId}")
    suspend fun updateCard(
        @Path("clientId") clientId: Int,
        @Path("cardId") cardId: Int,
        @Body card: Card,
    ): Flow<GenericResponse>
}
