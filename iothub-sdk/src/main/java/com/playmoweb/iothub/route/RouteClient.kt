package com.playmoweb.iothub.route

import com.playmoweb.iothub.IotHubRegion
import com.playmoweb.iothub.IotHubClient
import com.playmoweb.iothub.route.model.CreateRouteRequestBody
import com.playmoweb.iothub.route.model.ListRoutesResponse
import com.playmoweb.iothub.route.model.Route
import com.playmoweb.iothub.route.model.UpdateRouteRequestBody
import io.ktor.client.statement.HttpResponse
import kotlin.uuid.Uuid

interface RouteClient {
    /**
     * List routes
     * @see [Documentation](https://www.scaleway.com/en/developers/api/iot/#path-iot-routes-list-routes)
     * @param region [IotHubRegion] Default is FR_PAR
     * @return [ListRoutesResponse]
     */
    suspend fun listRoutes(
        region: IotHubRegion = IotHubClient.DEFAULT_REGION
    ): ListRoutesResponse

    /**
     * Create a route
     * @see [Documentation](https://www.scaleway.com/en/developers/api/iot/#path-iot-routes-create-a-route)
     * @param routeToAdd [CreateRouteRequestBody]
     * @param region [IotHubRegion] Default is FR_PAR
     * @return [Route]
     */
    suspend fun createRoute(routeToAdd: CreateRouteRequestBody, region: IotHubRegion = IotHubClient.DEFAULT_REGION): Route

    /**
     * Get a route
     * @see [Documentation](https://www.scaleway.com/en/developers/api/iot/#path-iot-routes-get-a-route)
     * @param routeId [Uuid]
     * @param region [IotHubRegion] Default is FR_PAR
     * @return [Route]
     */
    suspend fun getRoute(routeId: Uuid, region: IotHubRegion = IotHubClient.DEFAULT_REGION): Route

    /**
     * Update a route
     * @see [Documentation](https://www.scaleway.com/en/developers/api/iot/#path-iot-routes-update-a-route)
     * @param routeId [Uuid]
     * @param routeToUpdate [UpdateRouteRequestBody]
     * @param region [IotHubRegion] Default is FR_PAR
     * @return [Route]
     */
    suspend fun updateRoute(routeId: Uuid, routeToUpdate: UpdateRouteRequestBody, region: IotHubRegion = IotHubClient.DEFAULT_REGION): Route

    /**
     * Delete a route
     * @see [Documentation](https://www.scaleway.com/en/developers/api/iot/#path-iot-routes-delete-a-route)
     * @param routeId [Uuid]
     * @param region [IotHubRegion] Default is FR_PAR
     * @return [HttpResponse]
     */
    suspend fun deleteRoute(routeId: Uuid, region: IotHubRegion = IotHubClient.DEFAULT_REGION): HttpResponse


}
