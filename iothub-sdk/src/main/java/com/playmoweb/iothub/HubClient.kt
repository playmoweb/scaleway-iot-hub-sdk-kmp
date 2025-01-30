package com.playmoweb.iothub

import com.playmoweb.iothub.model.hub.ListHubsResponse

interface HubClient {
    /**
     * List hubs
     * @see [Documentation](https://www.scaleway.com/en/developers/api/iot/#path-iot-hubs-list-hubs)
     * @return [ListHubsResponse]
     */
    suspend fun listHubs(
        region: IotHubRegion = IotHubClient.DEFAULT_REGION
    ): ListHubsResponse
}
