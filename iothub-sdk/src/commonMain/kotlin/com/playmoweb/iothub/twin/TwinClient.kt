package com.playmoweb.iothub.twin

import com.playmoweb.iothub.IotHubClient
import com.playmoweb.iothub.IotHubRegion
import com.playmoweb.iothub.route.model.ListRoutesResponse
import com.playmoweb.iothub.twin.model.TwinDocument
import com.playmoweb.iothub.twin.model.TwinDocumentsResponse
import com.playmoweb.iothub.twin.model.UpdateTwinDocumentRequestBody
import io.ktor.client.statement.HttpResponse
import kotlin.uuid.Uuid


@ExperimentalTwin
interface TwinClient {

    /**
     * List the documents of a Cloud Twin
     * @see [Documentation](https://www.scaleway.com/en/developers/api/iot/#path-iot-cloud-twins-beta-list-the-documents-of-a-cloud-twin)
     * @param twinId [Uuid]
     * @param region [IotHubRegion] Default is FR_PAR
     * @return [ListRoutesResponse]
     */
    @ExperimentalTwin
    suspend fun listTwinDocuments(
        twinId: Uuid,
        region: IotHubRegion = IotHubClient.DEFAULT_REGION
    ): TwinDocumentsResponse

    /**
     * Delete all the documents of a Cloud Twin
     * @see [Documentation](https://www.scaleway.com/en/developers/api/iot/#path-iot-cloud-twins-beta-delete-all-the-documents-of-a-cloud-twin)
     * @param twinId [Uuid]
     * @param region [IotHubRegion] Default is FR_PAR
     * @return [HttpResponse]
     */
    @ExperimentalTwin
    suspend fun deleteTwinDocuments(
        twinId: Uuid,
        region: IotHubRegion = IotHubClient.DEFAULT_REGION
    ): HttpResponse

    /**
     * Get a Cloud Twin Document
     * @see [Documentation](https://www.scaleway.com/en/developers/api/iot/#path-iot-cloud-twins-beta-get-a-cloud-twin-document)
     * @param twinId [Uuid]
     * @param documentName [String]
     * @param region [IotHubRegion] Default is FR_PAR
     * @return [TwinDocument]
     */
    @ExperimentalTwin
    suspend fun getTwinDocument(
        twinId: Uuid,
        documentName: String,
        region: IotHubRegion = IotHubClient.DEFAULT_REGION
    ): TwinDocument

    /**
     * Update a Cloud Twin Document
     * @see [Documentation](https://www.scaleway.com/en/developers/api/iot/#path-iot-cloud-twins-beta-update-a-cloud-twin-document)
     * @param twinId [Uuid]
     * @param documentName [String]
     * @param documentToUpdate [UpdateTwinDocumentRequestBody]
     * @param region [IotHubRegion] Default is FR_PAR
     * @return [TwinDocument]
     */
    @ExperimentalTwin
    suspend fun updateTwinDocument(
        twinId: Uuid,
        documentName: String,
        documentToUpdate: UpdateTwinDocumentRequestBody,
        region: IotHubRegion = IotHubClient.DEFAULT_REGION
    ): TwinDocument

    /**
     * Patch a Cloud Twin Document
     *
     * Version : The version of the document to update. If set, ensures that the current version of the document matches before persisting the update.
     *
     * Data : Patch data. A json data that will be applied on the document's current data. Patching rules:
     * The patch goes recursively through the patch objects.
     * If the patch object property is null, it is removed from the final object.
     * If the patch object property is a value (number, strings, bool, arrays), it is replaced.
     * If the patch object property is an object, the previous rules will be applied recursively on it.
     *
     * @see [Documentation](https://www.scaleway.com/en/developers/api/iot/#path-iot-cloud-twins-beta-patch-a-cloud-twin-document)
     * @param twinId [Uuid]
     * @param documentName [String]
     * @param documentToPatch [UpdateTwinDocumentRequestBody]
     * @param region [IotHubRegion] Default is FR_PAR
     * @return [TwinDocument]
     */
    @ExperimentalTwin
    suspend fun patchTwinDocument(
        twinId: Uuid,
        documentName: String,
        documentToPatch: UpdateTwinDocumentRequestBody,
        region: IotHubRegion = IotHubClient.DEFAULT_REGION
    ): TwinDocument

    /**
     * Delete a Cloud Twin Document
     * @see [Documentation](https://www.scaleway.com/en/developers/api/iot/#path-iot-cloud-twins-beta-delete-a-cloud-twin-document)
     * @param twinId [Uuid]
     * @param documentName [String]
     * @param region [IotHubRegion] Default is FR_PAR
     * @return [HttpResponse]
     */
    @ExperimentalTwin
    suspend fun deleteTwinDocument(
        twinId: Uuid,
        documentName: String,
        region: IotHubRegion = IotHubClient.DEFAULT_REGION
    ): HttpResponse
}
