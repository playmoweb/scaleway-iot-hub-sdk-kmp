package com.playmoweb.iothub

import com.playmoweb.iothub.device.DeviceClient
import com.playmoweb.iothub.device.IotHubDeviceClient
import com.playmoweb.iothub.hub.HubClient
import com.playmoweb.iothub.hub.IotHubHubClient
import com.playmoweb.iothub.route.IotHubRouteClient
import com.playmoweb.iothub.route.RouteClient
import io.ktor.client.HttpClient

/**
 * IotHubClient
 * @param client [HttpClient]
 */
class IotHubClient(
    private val client: HttpClient,
    private val deviceClient: DeviceClient = IotHubDeviceClient(client),
    private val hubClient: HubClient = IotHubHubClient(client),
    private val routeClient: RouteClient = IotHubRouteClient(client)
): DeviceClient by deviceClient, HubClient by hubClient, RouteClient by routeClient {

    companion object {
        val DEFAULT_REGION = IotHubRegion.FR_PAR
    }

}
