package com.playmoweb.iothub

import com.playmoweb.iothub.model.hub.CreateHubRequestBody
import com.playmoweb.iothub.model.hub.Hub
import com.playmoweb.iothub.model.hub.ListHubsResponse
import com.playmoweb.iothub.model.hub.UpdateHubRequestBody
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.patch
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import kotlin.uuid.Uuid

class IotHubHubClient(
    private val client: HttpClient
): HubClient {

    companion object {
        const val HUB_ROUTE_PATH = "/hubs"
    }

    /**
     * List hubs
     * @see [Documentation](https://www.scaleway.com/en/developers/api/iot/#path-iot-hubs-list-hubs)
     * @return [ListHubsResponse]
     */
    override suspend fun listHubs(region: IotHubRegion): ListHubsResponse = client.get("${region.region}$HUB_ROUTE_PATH").body()

    /**
     * Create a hub
     * @see [Documentation](https://www.scaleway.com/en/developers/api/iot/#path-iot-hubs-create-a-hub)
     * @param hubToAdd [CreateHubRequestBody]
     * @param region [IotHubRegion] Default is FR_PAR
     * @return [Hub]
     */
    override suspend fun createHub(hubToAdd: CreateHubRequestBody, region: IotHubRegion): Hub = client.post("${region.region}$HUB_ROUTE_PATH") {
        setBody(hubToAdd)
    }.body()

    /**
     * Get a hub
     * @see [Documentation](https://www.scaleway.com/en/developers/api/iot/#path-iot-hubs-get-a-hub)
     * @param hubId [Uuid]
     * @param region [IotHubRegion] Default is FR_PAR
     * @return [Hub]
     */
    override suspend fun getHub(hubId: Uuid, region: IotHubRegion): Hub = client.get("${region.region}$HUB_ROUTE_PATH/$hubId").body()

    /**
     * Update a hub
     * @see [Documentation](https://www.scaleway.com/en/developers/api/iot/#path-iot-hubs-update-a-hub)
     * @param hubId [Uuid]
     * @param hubToUpdate [UpdateHubRequestBody]
     * @param region [IotHubRegion] Default is FR_PAR
     * @return [Hub]
     */
    override suspend fun updateHub(hubId: Uuid, hubToUpdate: UpdateHubRequestBody, region: IotHubRegion): Hub = client.patch("${region.region}$HUB_ROUTE_PATH/$hubId") {
        setBody(hubToUpdate)
    }.body()

    /**
     * Delete a hub
     * @see [Documentation](https://www.scaleway.com/en/developers/api/iot/#path-iot-hubs-delete-a-hub)
     * @param hubId [Uuid]
     * @return [HttpResponse]
     */
    override suspend fun deleteHub(hubId: Uuid, region: IotHubRegion): HttpResponse = client.delete("${region.region}$HUB_ROUTE_PATH/$hubId")
}
