package com.playmoweb.iothub

import com.playmoweb.iothub.device.DeviceClient
import com.playmoweb.iothub.device.IotHubDeviceClient
import com.playmoweb.iothub.hub.HubClient
import com.playmoweb.iothub.hub.IotHubHubClient
import com.playmoweb.iothub.network.IotHubNetworkClient
import com.playmoweb.iothub.network.NetworkClient
import com.playmoweb.iothub.route.IotHubRouteClient
import com.playmoweb.iothub.route.RouteClient
import com.playmoweb.iothub.twin.IotHubTwinClient
import com.playmoweb.iothub.twin.TwinClient
import io.ktor.client.HttpClient

/**
 * IotHubClient
 * @param client [HttpClient]
 */
class IotHubClient(
    private val client: HttpClient,
    private val deviceClient: DeviceClient = IotHubDeviceClient(client),
    private val hubClient: HubClient = IotHubHubClient(client),
    private val routeClient: RouteClient = IotHubRouteClient(client),
    private val networkClient: NetworkClient = IotHubNetworkClient(client),
    private val twinClient: TwinClient = IotHubTwinClient(client)
) : DeviceClient by deviceClient,
    HubClient by hubClient,
    RouteClient by routeClient,
    NetworkClient by networkClient,
    TwinClient by twinClient {

    companion object {
        val DEFAULT_REGION = IotHubRegion.FR_PAR
    }

}
