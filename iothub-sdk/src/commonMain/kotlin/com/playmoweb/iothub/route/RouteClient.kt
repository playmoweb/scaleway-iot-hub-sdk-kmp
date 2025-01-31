package com.playmoweb.iothub.route

import com.playmoweb.iothub.IotHubClient
import com.playmoweb.iothub.IotHubRegion
import com.playmoweb.iothub.route.model.CreateRouteRequestBody
import com.playmoweb.iothub.route.model.ListRoutesResponse
import com.playmoweb.iothub.route.model.Route
import com.playmoweb.iothub.route.model.RouteOrderBy
import com.playmoweb.iothub.route.model.UpdateRouteRequestBody
import io.ktor.client.statement.HttpResponse
import kotlin.uuid.Uuid

interface RouteClient {

    companion object {
        const val MAXIMUM_PAGE_SIZE = 100
    }

    /**
     * List routes
     * @see [Documentation](https://www.scaleway.com/en/developers/api/iot/#path-iot-routes-list-routes)
     * @param page [Int] Default is 1
     * @param pageSize [Int] Default is 100, Maximum is 100
     * @param orderBy [RouteOrderBy] Default is NAME_ASC
     * @param hubId [Uuid] Default is null
     * @param name [String] Default is null
     * @param region [IotHubRegion] Default is FR_PAR
     * @return [ListRoutesResponse]
     */
    suspend fun listRoutes(
        page: Int = 1,
        pageSize: Int = MAXIMUM_PAGE_SIZE,
        orderBy: RouteOrderBy = RouteOrderBy.NAME_ASC,
        hubId: Uuid? = null,
        name: String? = null,
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
