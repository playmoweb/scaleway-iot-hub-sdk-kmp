package com.playmoweb.iothub

import com.playmoweb.iothub.model.hub.CreateHubRequestBody
import com.playmoweb.iothub.model.hub.Hub
import com.playmoweb.iothub.model.hub.ListHubsResponse

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
}
