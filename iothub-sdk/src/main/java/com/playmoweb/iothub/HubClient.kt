package com.playmoweb.iothub

import com.playmoweb.iothub.model.hub.CreateHubRequestBody
import com.playmoweb.iothub.model.hub.Hub
import com.playmoweb.iothub.model.hub.ListHubsResponse
import com.playmoweb.iothub.model.hub.UpdateHubRequestBody
import io.ktor.client.statement.HttpResponse
import kotlin.uuid.Uuid

interface HubClient {
    /**
     * List hubs
     * @see [Documentation](https://www.scaleway.com/en/developers/api/iot/#path-iot-hubs-list-hubs)
     * @param region [IotHubRegion] Default is FR_PAR
     * @return [ListHubsResponse]
     */
    suspend fun listHubs(
        region: IotHubRegion = IotHubClient.DEFAULT_REGION
    ): ListHubsResponse

    /**
     * Create a hub
     * @see [Documentation](https://www.scaleway.com/en/developers/api/iot/#path-iot-hubs-create-a-hub)
     * @param hubToAdd [CreateHubRequestBody]
     * @param region [IotHubRegion] Default is FR_PAR
     * @return [Hub]
     */
    suspend fun createHub(hubToAdd: CreateHubRequestBody, region: IotHubRegion = IotHubClient.DEFAULT_REGION): Hub

    /**
     * Get a hub
     * @see [Documentation](https://www.scaleway.com/en/developers/api/iot/#path-iot-hubs-get-a-hub)
     * @param hubId [Uuid]
     * @param region [IotHubRegion] Default is FR_PAR
     * @return [Hub]
     */
    suspend fun getHub(hubId: Uuid, region: IotHubRegion = IotHubClient.DEFAULT_REGION): Hub

    /**
     * Update a hub
     * @see [Documentation](https://www.scaleway.com/en/developers/api/iot/#path-iot-hubs-update-a-hub)
     * @param hubId [Uuid]
     * @param hubToUpdate [UpdateHubRequestBody]
     * @param region [IotHubRegion] Default is FR_PAR
     * @return [Hub]
     */
    suspend fun updateHub(hubId: Uuid, hubToUpdate: UpdateHubRequestBody, region: IotHubRegion = IotHubClient.DEFAULT_REGION): Hub

    /**
     * Delete a hub
     * @see [Documentation](https://www.scaleway.com/en/developers/api/iot/#path-iot-hubs-delete-a-hub)
     * @param hubId [Uuid]
     * @return [HttpResponse]
     */
    suspend fun deleteHub(hubId: Uuid, region: IotHubRegion = IotHubClient.DEFAULT_REGION): HttpResponse
}
