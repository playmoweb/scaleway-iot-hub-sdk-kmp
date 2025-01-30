package com.playmoweb.iothub.route

import com.playmoweb.iothub.IotHubClient
import com.playmoweb.iothub.IotHubRegion
import com.playmoweb.iothub.route.model.CreateRouteRequestBody
import com.playmoweb.iothub.route.model.ListRoutesResponse
import com.playmoweb.iothub.route.model.Route
import com.playmoweb.iothub.route.model.UpdateRouteRequestBody
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.patch
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import kotlin.uuid.Uuid

class IotHubRouteClient(
    private val client: HttpClient
): RouteClient {

    companion object {
        const val ROUTE_ROUTE_PATH = "/routes"
    }

    /**
     * List routes
     * @see [Documentation](https://www.scaleway.com/en/developers/api/iot/#path-iot-routes-list-routes)
     * @param region [IotHubRegion] Default is FR_PAR
     * @return [ListRoutesResponse]
     */
    override suspend fun listRoutes(
        region: IotHubRegion
    ): ListRoutesResponse = client.get("${region.region}${ROUTE_ROUTE_PATH}").apply {
        println("listRoutes: ${bodyAsText()}")
    }.body()

    /**
     * Create a route
     * @see [Documentation](https://www.scaleway.com/en/developers/api/iot/#path-iot-routes-create-a-route)
     * @param routeToAdd [CreateRouteRequestBody]
     * @param region [IotHubRegion] Default is FR_PAR
     * @return [Route]
     */
    override suspend fun createRoute(routeToAdd: CreateRouteRequestBody, region: IotHubRegion): Route {
        require (!(routeToAdd.s3Config == null && routeToAdd.restConfig == null && routeToAdd.dbConfig == null)) {
            "At least one of the following fields must be set: s3Config, restConfig, dbConfig"
        }
        return client.post("${region.region}${ROUTE_ROUTE_PATH}") {
            setBody(routeToAdd)
        }.body()
    }

    /**
     * Get a route
     * @see [Documentation](https://www.scaleway.com/en/developers/api/iot/#path-iot-routes-get-a-route)
     * @param routeId [Uuid]
     * @param region [IotHubRegion] Default is FR_PAR
     * @return [Route]
     */
    override suspend fun getRoute(routeId: Uuid, region: IotHubRegion): Route = client.get("${region.region}${ROUTE_ROUTE_PATH}/$routeId").body()

    /**
     * Update a route
     * @see [Documentation](https://www.scaleway.com/en/developers/api/iot/#path-iot-routes-update-a-route)
     * @param routeId [Uuid]
     * @param routeToUpdate [UpdateRouteRequestBody]
     * @param region [IotHubRegion] Default is FR_PAR
     * @return [Route]
     */
    override suspend fun updateRoute(routeId: Uuid, routeToUpdate: UpdateRouteRequestBody, region: IotHubRegion): Route = client.patch("${region.region}${ROUTE_ROUTE_PATH}/$routeId") {
        setBody(routeToUpdate)
    }.body()

    /**
     * Delete a route
     * @see [Documentation](https://www.scaleway.com/en/developers/api/iot/#path-iot-routes-delete-a-route)
     * @param routeId [Uuid]
     * @param region [IotHubRegion] Default is FR_PAR
     * @return [HttpResponse]
     */
    override suspend fun deleteRoute(routeId: Uuid, region: IotHubRegion): HttpResponse = client.delete("${region.region}${ROUTE_ROUTE_PATH}/$routeId")
}
