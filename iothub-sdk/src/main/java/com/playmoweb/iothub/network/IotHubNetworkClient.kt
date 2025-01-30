package com.playmoweb.iothub.network

import com.playmoweb.iothub.IotHubClient
import com.playmoweb.iothub.IotHubRegion
import com.playmoweb.iothub.network.model.CreateNetworkRequestBody
import com.playmoweb.iothub.network.model.CreateNetworkResponse
import com.playmoweb.iothub.network.model.ListNetworksResponse
import com.playmoweb.iothub.network.model.Network
import com.playmoweb.iothub.network.model.NetworkOrderBy
import com.playmoweb.iothub.route.model.Route
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import kotlin.uuid.Uuid

class IotHubNetworkClient(
    val client: HttpClient
): NetworkClient {

    companion object {
        const val NETWORK_ROUTE_PATH = "/networks"
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
    override suspend fun listNetworks(
        page: Int,
        pageSize: Int,
        orderBy: NetworkOrderBy,
        hubId: Uuid?,
        topicPrefix: String?,
        name: String?,
        region: IotHubRegion
    ): ListNetworksResponse = client
        .get("${region.region}$NETWORK_ROUTE_PATH") {
            parameter("page", page)
            parameter("page_size", pageSize)
            parameter("order_by", orderBy.value)
            hubId?.let { parameter("hub_id", it) }
            topicPrefix?.let { parameter("topic_prefix", it) }
            name?.let { parameter("name", it) }
        }
        .body()

    /**
     * Create a network
     * @see [Documentation](https://www.scaleway.com/en/developers/api/iot/#path-iot-networks-create-a-new-network)
     * @param networkToAdd [CreateNetworkRequestBody]
     * @param region [IotHubRegion] Default is FR_PAR
     * @return [CreateNetworkResponse]
     */
    override suspend fun createNetwork(networkToAdd: CreateNetworkRequestBody, region: IotHubRegion): CreateNetworkResponse = client
        .post("${region.region}$NETWORK_ROUTE_PATH") {
            setBody(networkToAdd)
        }.body()

    /**
     * Retrieve a specific network
     * Retrieve an existing network, specified by its network ID. The response returns full details of the network, including its type, the topic prefix and its endpoint.
     * @see [Documentation](https://www.scaleway.com/en/developers/api/iot/#path-iot-networks-retrieve-a-specific-network)
     * @param networkId [Uuid]
     * @param region [IotHubRegion] Default is FR_PAR
     * @return [Network]
     */
    override suspend fun getNetwork(networkId: Uuid, region: IotHubRegion): Network = client.get("${region.region}$NETWORK_ROUTE_PATH/$networkId").body()

    /**
     * Delete a Network
     * Delete an existing network, specified by its network ID. Deleting a network is permanent, and cannot be undone.
     * @see [Documentation](https://www.scaleway.com/en/developers/api/iot/#path-iot-networks-delete-a-network)
     * @param networkId [Uuid]
     * @param region [IotHubRegion] Default is FR_PAR
     * @return [HttpResponse]
     */
    override suspend fun deleteNetwork(networkId: Uuid, region: IotHubRegion): HttpResponse = client.delete("${region.region}$NETWORK_ROUTE_PATH/$networkId")
}
