package com.playmoweb.iothub.hub

import com.playmoweb.iothub.IotHubRegion
import com.playmoweb.iothub.hub.model.CertificateAuthority
import com.playmoweb.iothub.hub.model.CreateHubRequestBody
import com.playmoweb.iothub.hub.model.Hub
import com.playmoweb.iothub.hub.model.HubOrderBy
import com.playmoweb.iothub.hub.model.ListHubsResponse
import com.playmoweb.iothub.hub.model.SetCertificateAuthorityBody
import com.playmoweb.iothub.hub.model.UpdateHubRequestBody
import com.playmoweb.iothub.model.Metrics
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.patch
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import kotlinx.datetime.Instant
import kotlin.uuid.Uuid

class IotHubHubClient(
    private val client: HttpClient
): HubClient {

    companion object {
        const val HUB_ROUTE_PATH = "/hubs"
    }

    /**
     * List hubs
     * @see [Documentation](https://www.scaleway.com/en/developers/api/iot/#path-iot-hubs-list-hubs)
     * @param page [Int] Default is 1
     * @param pageSize [Int] Default is 100, Maximum is 100
     * @param orderBy [HubOrderBy] Default is NAME_ASC
     * @param projectId [Uuid] Default is null
     * @param organizationId [Uuid] Default is null
     * @param name [String] Default is null
     * @param region [IotHubRegion] Default is FR_PAR
     * @return [ListHubsResponse]
     */
    override suspend fun listHubs(
        page: Int,
        pageSize: Int,
        orderBy: HubOrderBy,
        projectId: Uuid?,
        organizationId: Uuid?,
        name: String?,
        region: IotHubRegion
    ): ListHubsResponse = client
        .get("${region}$HUB_ROUTE_PATH") {
            parameter("page", page)
            parameter("page_size", pageSize)
            parameter("order_by", orderBy.toString())
            projectId?.let { parameter("project_id", it) }
            organizationId?.let { parameter("organization_id", it) }
            name?.let { parameter("name", it) }
        }
        .body()

    /**
     * Create a hub
     * @see [Documentation](https://www.scaleway.com/en/developers/api/iot/#path-iot-hubs-create-a-hub)
     * @param hubToAdd [CreateHubRequestBody]
     * @param region [IotHubRegion] Default is FR_PAR
     * @return [Hub]
     */
    override suspend fun createHub(hubToAdd: CreateHubRequestBody, region: IotHubRegion): Hub = client.post("${region}$HUB_ROUTE_PATH") {
        setBody(hubToAdd)
    }.body()

    /**
     * Get a hub
     * @see [Documentation](https://www.scaleway.com/en/developers/api/iot/#path-iot-hubs-get-a-hub)
     * @param hubId [Uuid]
     * @param region [IotHubRegion] Default is FR_PAR
     * @return [Hub]
     */
    override suspend fun getHub(hubId: Uuid, region: IotHubRegion): Hub = client.get("${region}$HUB_ROUTE_PATH/$hubId").body()

    /**
     * Update a hub
     * @see [Documentation](https://www.scaleway.com/en/developers/api/iot/#path-iot-hubs-update-a-hub)
     * @param hubId [Uuid]
     * @param hubToUpdate [UpdateHubRequestBody]
     * @param region [IotHubRegion] Default is FR_PAR
     * @return [Hub]
     */
    override suspend fun updateHub(hubId: Uuid, hubToUpdate: UpdateHubRequestBody, region: IotHubRegion): Hub = client.patch("${region}$HUB_ROUTE_PATH/$hubId") {
        setBody(hubToUpdate)
    }.body()

    /**
     * Delete a hub
     * @see [Documentation](https://www.scaleway.com/en/developers/api/iot/#path-iot-hubs-delete-a-hub)
     * @param hubId [Uuid]
     * @param region [IotHubRegion] Default is FR_PAR
     * @return [HttpResponse]
     */
    override suspend fun deleteHub(hubId: Uuid, region: IotHubRegion): HttpResponse = client.delete("${region}$HUB_ROUTE_PATH/$hubId")

    /**
     * Get the certificate authority of a hub
     * @see [Documentation](https://www.scaleway.com/en/developers/api/iot/#path-iot-hubs-get-the-certificate-authority-of-a-hub)
     * @param hubId [Uuid]
     * @param region [IotHubRegion] Default is FR_PAR
     * @return [CertificateAuthority]
     */
    override suspend fun getHubCertificateAuthority(hubId: Uuid, region: IotHubRegion): CertificateAuthority = client.get("${region}$HUB_ROUTE_PATH/$hubId/ca").body()

    /**
     * Set the certificate authority of a hub
     * @see [Documentation](https://www.scaleway.com/en/developers/api/iot/#path-iot-hubs-set-the-certificate-authority-of-a-hub)
     * @param hubId [Uuid]
     * @param certificate [SetCertificateAuthorityBody]
     * @param region [IotHubRegion] Default is FR_PAR
     * @return [Hub]
     */
    override suspend fun setHubCertificateAuthority(hubId: Uuid, certificate: SetCertificateAuthorityBody, region: IotHubRegion): Hub = client.post("${region}$HUB_ROUTE_PATH/$hubId/ca") {
        setBody(certificate)
    }.body()

    /**
     * Disable a hub
     * @see [Documentation](https://www.scaleway.com/en/developers/api/iot/#path-iot-hubs-disable-a-hub)
     * @param hubId [Uuid]
     * @param region [IotHubRegion] Default is FR_PAR
     * @return [Hub]
     */
    override suspend fun disableHub(hubId: Uuid, region: IotHubRegion): Hub = client.post("${region}$HUB_ROUTE_PATH/$hubId/disable").body()

    /**
     * Enable a hub
     * @see [Documentation](https://www.scaleway.com/en/developers/api/iot/#path-iot-hubs-enable-a-hub)
     * @param hubId [Uuid]
     * @param region [IotHubRegion] Default is FR_PAR
     * @return [Hub]
     */
    override suspend fun enableHub(hubId: Uuid, region: IotHubRegion): Hub = client.post("${region}$HUB_ROUTE_PATH/$hubId/enable").body()

    /**
     * Get hub metrics
     * @see [Documentation](https://www.scaleway.com/en/developers/api/iot/#path-iot-hubs-get-a-hubs-metrics
     * @param hubId [Uuid]
     * @param startDate: [Instant]
     * @param region [IotHubRegion] Default is FR_PAR
     * @return [Metrics]
     */
    override suspend fun getHubMetrics(
        hubId: Uuid,
        startDate: Instant,
        region: IotHubRegion
    ): Metrics = client.get("${region}$HUB_ROUTE_PATH/$hubId/metrics?start_date=$startDate").body()
}
