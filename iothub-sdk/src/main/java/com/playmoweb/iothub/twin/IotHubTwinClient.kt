package com.playmoweb.iothub.twin

import com.playmoweb.iothub.IotHubRegion
import com.playmoweb.iothub.route.model.ListRoutesResponse
import com.playmoweb.iothub.twin.model.TwinDocument
import com.playmoweb.iothub.twin.model.TwinDocumentsResponse
import com.playmoweb.iothub.twin.model.UpdateTwinDocumentRequestBody
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.patch
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@ExperimentalTwin
@ExperimentalUuidApi
class IotHubTwinClient(
    private val client: HttpClient
): TwinClient {

    companion object {
        const val TWIN_ROUTE_PATH = "/twins"
    }

    /**
     * List the documents of a Cloud Twin
     * @see [Documentation](https://www.scaleway.com/en/developers/api/iot/#path-iot-cloud-twins-beta-list-the-documents-of-a-cloud-twin)
     * @param twinId [Uuid]
     * @param region [IotHubRegion] Default is FR_PAR
     * @return [ListRoutesResponse]
     */
    @ExperimentalTwin
    override suspend fun listTwinDocuments(twinId: Uuid, region: IotHubRegion): TwinDocumentsResponse = client
        .get("${region.region}$TWIN_ROUTE_PATH/$twinId")
        .body()

    /**
     * Delete all the documents of a Cloud Twin
     * @see [Documentation](https://www.scaleway.com/en/developers/api/iot/#path-iot-cloud-twins-beta-delete-all-the-documents-of-a-cloud-twin)
     * @param twinId [Uuid]
     * @param region [IotHubRegion] Default is FR_PAR
     * @return [HttpResponse]
     */
    @ExperimentalTwin
    override suspend fun deleteTwinDocuments(twinId: Uuid, region: IotHubRegion): HttpResponse = client
        .delete("${region.region}$TWIN_ROUTE_PATH/$twinId")

    /**
     * Get a Cloud Twin Document
     * @see [Documentation](https://www.scaleway.com/en/developers/api/iot/#path-iot-cloud-twins-beta-get-a-cloud-twin-document)
     * @param twinId [Uuid]
     * @param documentName [String]
     * @param region [IotHubRegion] Default is FR_PAR
     * @return [TwinDocument]
     */
    @ExperimentalTwin
    override suspend fun getTwinDocument(twinId: Uuid, documentName: String, region: IotHubRegion): TwinDocument = client
        .get("${region.region}$TWIN_ROUTE_PATH/$twinId/documents/$documentName")
        .body()

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
    override suspend fun updateTwinDocument(twinId: Uuid, documentName: String, documentToUpdate: UpdateTwinDocumentRequestBody, region: IotHubRegion): TwinDocument = client
        .put("${region.region}$TWIN_ROUTE_PATH/$twinId/documents/$documentName") {
            setBody(documentToUpdate)
        }
        .body()

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
    override suspend fun patchTwinDocument(twinId: Uuid, documentName: String, documentToPatch: UpdateTwinDocumentRequestBody, region: IotHubRegion): TwinDocument = client
        .patch("${region.region}$TWIN_ROUTE_PATH/$twinId/documents/$documentName/patch") {
            setBody(documentToPatch)
        }
        .body()

    /**
     * Delete a Cloud Twin Document
     * @see [Documentation](https://www.scaleway.com/en/developers/api/iot/#path-iot-cloud-twins-beta-delete-a-cloud-twin-document)
     * @param twinId [Uuid]
     * @param documentName [String]
     * @param region [IotHubRegion] Default is FR_PAR
     * @return [HttpResponse]
     */
    @ExperimentalTwin
    override suspend fun deleteTwinDocument(twinId: Uuid, documentName: String, region: IotHubRegion): HttpResponse = client
        .delete("${region.region}$TWIN_ROUTE_PATH/$twinId/documents/$documentName")
}
