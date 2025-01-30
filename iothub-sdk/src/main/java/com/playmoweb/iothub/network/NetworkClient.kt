package com.playmoweb.iothub.network

import com.playmoweb.iothub.IotHubClient
import com.playmoweb.iothub.IotHubRegion
import com.playmoweb.iothub.network.model.CreateNetworkRequestBody
import com.playmoweb.iothub.network.model.CreateNetworkResponse
import com.playmoweb.iothub.network.model.ListNetworksResponse
import com.playmoweb.iothub.network.model.Network
import com.playmoweb.iothub.network.model.NetworkOrderBy
import com.playmoweb.iothub.route.model.Route
import io.ktor.client.statement.HttpResponse
import kotlin.uuid.Uuid

interface NetworkClient {

    companion object {
        const val MAXIMUM_PAGE_SIZE = 100
    }

    /**
     * List networks
     * @see [Documentation](https://www.scaleway.com/en/developers/api/iot/#path-iot-networks-list-the-networks)
     * @param page [Int] Default is 1
     * @param pageSize [Int] Default is 100, Maximum is 100
     * @param orderBy [NetworkOrderBy] Default is NAME_ASC
     * @param hubId [Uuid] Default is null
     * @param topicPrefix [String] Default is null
     * @param name [String] Default is null
     * @param region [IotHubRegion] Default is FR_PAR
     * @return [ListNetworksResponse]
     */
    suspend fun listNetworks(
        page: Int = 1,
        pageSize: Int = MAXIMUM_PAGE_SIZE,
        orderBy: NetworkOrderBy = NetworkOrderBy.NAME_ASC,
        hubId: Uuid? = null,
        topicPrefix: String? = null,
        name: String? = null,
        region: IotHubRegion = IotHubClient.DEFAULT_REGION
    ): ListNetworksResponse

    /**
     * Create a network
     * @see [Documentation](https://www.scaleway.com/en/developers/api/iot/#path-iot-networks-create-a-new-network)
     * @param networkToAdd [CreateNetworkRequestBody]
     * @param region [IotHubRegion] Default is FR_PAR
     * @return [CreateNetworkResponse]
     */
    suspend fun createNetwork(networkToAdd: CreateNetworkRequestBody, region: IotHubRegion = IotHubClient.DEFAULT_REGION): CreateNetworkResponse

    /**
     * Retrieve a specific network
     * Retrieve an existing network, specified by its network ID. The response returns full details of the network, including its type, the topic prefix and its endpoint.
     * @see [Documentation](https://www.scaleway.com/en/developers/api/iot/#path-iot-networks-retrieve-a-specific-network)
     * @param networkId [Uuid]
     * @param region [IotHubRegion] Default is FR_PAR
     * @return [Network]
     */
    suspend fun getNetwork(networkId: Uuid, region: IotHubRegion = IotHubClient.DEFAULT_REGION): Network

    /**
     * Delete a Network
     * Delete an existing network, specified by its network ID. Deleting a network is permanent, and cannot be undone.
     * @see [Documentation](https://www.scaleway.com/en/developers/api/iot/#path-iot-networks-delete-a-network)
     * @param networkId [Uuid]
     * @param region [IotHubRegion] Default is FR_PAR
     * @return [HttpResponse]
     */
    suspend fun deleteNetwork(networkId: Uuid, region: IotHubRegion = IotHubClient.DEFAULT_REGION): HttpResponse
}
