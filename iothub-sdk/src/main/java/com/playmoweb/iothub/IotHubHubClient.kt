package com.playmoweb.iothub

import com.playmoweb.iothub.model.Metrics
import com.playmoweb.iothub.model.hub.CertificateAuthority
import com.playmoweb.iothub.model.hub.CreateHubRequestBody
import com.playmoweb.iothub.model.hub.Hub
import com.playmoweb.iothub.model.hub.ListHubsResponse
import com.playmoweb.iothub.model.hub.SetCertificateAuthorityBody
import com.playmoweb.iothub.model.hub.UpdateHubRequestBody
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
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
     * @return [ListHubsResponse]
     */
    override suspend fun listHubs(region: IotHubRegion): ListHubsResponse = client.get("${region.region}$HUB_ROUTE_PATH").body()

    /**
     * Create a hub
     * @see [Documentation](https://www.scaleway.com/en/developers/api/iot/#path-iot-hubs-create-a-hub)
     * @param hubToAdd [CreateHubRequestBody]
     * @param region [IotHubRegion] Default is FR_PAR
     * @return [Hub]
     */
    override suspend fun createHub(hubToAdd: CreateHubRequestBody, region: IotHubRegion): Hub = client.post("${region.region}$HUB_ROUTE_PATH") {
        setBody(hubToAdd)
    }.body()

    /**
     * Get a hub
     * @see [Documentation](https://www.scaleway.com/en/developers/api/iot/#path-iot-hubs-get-a-hub)
     * @param hubId [Uuid]
     * @param region [IotHubRegion] Default is FR_PAR
     * @return [Hub]
     */
    override suspend fun getHub(hubId: Uuid, region: IotHubRegion): Hub = client.get("${region.region}$HUB_ROUTE_PATH/$hubId").body()

    /**
     * Update a hub
     * @see [Documentation](https://www.scaleway.com/en/developers/api/iot/#path-iot-hubs-update-a-hub)
     * @param hubId [Uuid]
     * @param hubToUpdate [UpdateHubRequestBody]
     * @param region [IotHubRegion] Default is FR_PAR
     * @return [Hub]
     */
    override suspend fun updateHub(hubId: Uuid, hubToUpdate: UpdateHubRequestBody, region: IotHubRegion): Hub = client.patch("${region.region}$HUB_ROUTE_PATH/$hubId") {
        setBody(hubToUpdate)
    }.body()

    /**
     * Delete a hub
     * @see [Documentation](https://www.scaleway.com/en/developers/api/iot/#path-iot-hubs-delete-a-hub)
     * @param hubId [Uuid]
     * @param region [IotHubRegion] Default is FR_PAR
     * @return [HttpResponse]
     */
    override suspend fun deleteHub(hubId: Uuid, region: IotHubRegion): HttpResponse = client.delete("${region.region}$HUB_ROUTE_PATH/$hubId")

    /**
     * Get the certificate authority of a hub
     * @see [Documentation](https://www.scaleway.com/en/developers/api/iot/#path-iot-hubs-get-the-certificate-authority-of-a-hub)
     * @param hubId [Uuid]
     * @param region [IotHubRegion] Default is FR_PAR
     * @return [CertificateAuthority]
     */
    override suspend fun getHubCertificateAuthority(hubId: Uuid, region: IotHubRegion): CertificateAuthority = client.get("${region.region}$HUB_ROUTE_PATH/$hubId/ca").body()

    /**
     * Set the certificate authority of a hub
     * @see [Documentation](https://www.scaleway.com/en/developers/api/iot/#path-iot-hubs-set-the-certificate-authority-of-a-hub)
     * @param hubId [Uuid]
     * @param certificate [SetCertificateAuthorityBody]
     * @param region [IotHubRegion] Default is FR_PAR
     * @return [Hub]
     */
    override suspend fun setHubCertificateAuthority(hubId: Uuid, certificate: SetCertificateAuthorityBody, region: IotHubRegion): Hub = client.post("${region.region}$HUB_ROUTE_PATH/$hubId/ca") {
        setBody(certificate)
    }.body()

    /**
     * Disable a hub
     * @see [Documentation](https://www.scaleway.com/en/developers/api/iot/#path-iot-hubs-disable-a-hub)
     * @param hubId [Uuid]
     * @param region [IotHubRegion] Default is FR_PAR
     * @return [Hub]
     */
    override suspend fun disableHub(hubId: Uuid, region: IotHubRegion): Hub = client.post("${region.region}$HUB_ROUTE_PATH/$hubId/disable").body()

    /**
     * Enable a hub
     * @see [Documentation](https://www.scaleway.com/en/developers/api/iot/#path-iot-hubs-enable-a-hub)
     * @param hubId [Uuid]
     * @param region [IotHubRegion] Default is FR_PAR
     * @return [Hub]
     */
    override suspend fun enableHub(hubId: Uuid, region: IotHubRegion): Hub = client.post("${region.region}$HUB_ROUTE_PATH/$hubId/enable").body()

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
    ): Metrics = client.get("${region.region}$HUB_ROUTE_PATH/$hubId/metrics?start_date=$startDate").body()
}
