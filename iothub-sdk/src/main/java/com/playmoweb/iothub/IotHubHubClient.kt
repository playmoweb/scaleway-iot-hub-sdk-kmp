package com.playmoweb.iothub

import com.playmoweb.iothub.model.hub.ListHubsResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

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
}
