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

import de.jensklingenberg.ktorfit.http.DELETE
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.Multipart
import de.jensklingenberg.ktorfit.http.POST
import de.jensklingenberg.ktorfit.http.PUT
import de.jensklingenberg.ktorfit.http.Part
import de.jensklingenberg.ktorfit.http.Path
import io.ktor.http.content.PartData
import kotlinx.coroutines.flow.Flow
import org.mifospay.core.model.entity.noncore.Document
import org.mifospay.core.network.ApiEndPoints

interface DocumentService {
    @GET("{entityType}/{entityId}/" + ApiEndPoints.DOCUMENTS)
    fun getDocuments(
        @Path("entityType") entityType: String,
        @Path("entityId") entityId: Int,
    ): Flow<List<Document>>

    /**
     * @param entityType     - Type for which document is being uploaded (Client, Loan
     * or Savings etc)
     * @param entityId       - Id of Entity
     * @param nameOfDocument - Document Name
     * @param description    - Mandatory - Document Description
     * @param typedFile      - Mandatory
     */
    @POST("{entityType}/{entityId}/" + ApiEndPoints.DOCUMENTS)
    @Multipart
    fun createDocument(
        @Path("entityType") entityType: String,
        @Path("entityId") entityId: Long,
        @Part("name") nameOfDocument: String,
        @Part("description") description: String,
        @Part typedFile: PartData,
    )

    /**
     * This Service is for downloading the Document with EntityType and EntityId and Document Id
     * Rest End Point :
     * https://demo.openmf.org/fineract-provider/api/v1/{entityType}/{entityId}/documents/
     * {documentId}/attachment
     *
     * @param entityType - Type for which document is being uploaded (Client, Loan
     * or Savings etc)
     * @param entityId   - Id of Entity
     * @param documentId - Document Id
     * @return ResponseBody
     */
    @GET("{entityType}/{entityId}/" + ApiEndPoints.DOCUMENTS + "/{documentId}/attachment")
    fun downloadDocument(
        @Path("entityType") entityType: String,
        @Path("entityId") entityId: Int,
        @Path("documentId") documentId: Int,
    ): Flow<Document>

    /**
     * This Service is for Deleting the Document with EntityType and EntityId and Document Id.
     * Rest End Point :
     * https://demo.openmf.org/fineract-provider/api/v1/{entityType}/{entityId}/documents/
     * {documentId}
     *
     * @param entityType - Type for which document is being uploaded (Client, Loan
     * or Savings etc)
     * @param entityId   - Id of Entity
     * @param documentId - Document Id
     */
    @DELETE("{entityType}/{entityId}/" + ApiEndPoints.DOCUMENTS + "/{documentId}")
    fun removeDocument(
        @Path("entityType") entityType: String,
        @Path("entityId") entityId: Int,
        @Path("documentId") documentId: Int,
    )

    /**
     * This Service for Updating the Document with EntityType and EntityId and Document Id.
     * Rest End Point :
     * PUT
     * https://demo.openmf.org/fineract-provider/api/v1/{entityType}/{entityId}/documents/
     * {documentId}
     *
     * @param entityType     - Type for which document is being uploaded (Client, Loan
     * or Savings etc)
     * @param entityId       - Id of Entity
     * @param documentId     - Id of document
     * @param nameOfDocument - Document Name
     * @param description    - Mandatory - Document Description
     * @param typedFile      - Mandatory
     */
    @PUT("{entityType}/{entityId}/" + ApiEndPoints.DOCUMENTS + "/{documentId}")
    @Multipart
    fun updateDocument(
        @Path("entityType") entityType: String,
        @Path("entityId") entityId: Int,
        @Path("documentId") documentId: Int,
        @Part("name") nameOfDocument: String,
        @Part("description") description: String,
        @Part typedFile: PartData,
    )
}
