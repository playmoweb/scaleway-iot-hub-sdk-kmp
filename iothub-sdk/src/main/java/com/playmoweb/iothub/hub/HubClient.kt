package com.playmoweb.iothub.hub

import com.playmoweb.iothub.IotHubClient
import com.playmoweb.iothub.IotHubRegion
import com.playmoweb.iothub.model.Metrics
import com.playmoweb.iothub.hub.model.CertificateAuthority
import com.playmoweb.iothub.hub.model.CreateHubRequestBody
import com.playmoweb.iothub.hub.model.Hub
import com.playmoweb.iothub.hub.model.HubOrderBy
import com.playmoweb.iothub.hub.model.ListHubsResponse
import com.playmoweb.iothub.hub.model.SetCertificateAuthorityBody
import com.playmoweb.iothub.hub.model.UpdateHubRequestBody
import io.ktor.client.statement.HttpResponse
import kotlinx.datetime.Instant
import kotlin.uuid.Uuid

interface HubClient {

    companion object {
        const val MAXIMUM_PAGE_SIZE = 100
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
    suspend fun listHubs(
        page: Int = 1,
        pageSize: Int = MAXIMUM_PAGE_SIZE,
        orderBy: HubOrderBy = HubOrderBy.NAME_ASC,
        projectId: Uuid? = null,
        organizationId: Uuid? = null,
        name: String? = null,
        region: IotHubRegion = IotHubClient.DEFAULT_REGION
    ): ListHubsResponse

    /**
     * Create a hub
     * @see [Documentation](https://www.scaleway.com/en/developers/api/iot/#path-iot-hubs-create-a-hub)
     * @param hubToAdd [CreateHubRequestBody]
     * @param region [IotHubRegion] Default is FR_PAR
     * @return [Hub]
     */
    suspend fun createHub(hubToAdd: CreateHubRequestBody, region: IotHubRegion = IotHubClient.DEFAULT_REGION): Hub

    /**
     * Get a hub
     * @see [Documentation](https://www.scaleway.com/en/developers/api/iot/#path-iot-hubs-get-a-hub)
     * @param hubId [Uuid]
     * @param region [IotHubRegion] Default is FR_PAR
     * @return [Hub]
     */
    suspend fun getHub(hubId: Uuid, region: IotHubRegion = IotHubClient.DEFAULT_REGION): Hub

    /**
     * Update a hub
     * @see [Documentation](https://www.scaleway.com/en/developers/api/iot/#path-iot-hubs-update-a-hub)
     * @param hubId [Uuid]
     * @param hubToUpdate [UpdateHubRequestBody]
     * @param region [IotHubRegion] Default is FR_PAR
     * @return [Hub]
     */
    suspend fun updateHub(hubId: Uuid, hubToUpdate: UpdateHubRequestBody, region: IotHubRegion = IotHubClient.DEFAULT_REGION): Hub

    /**
     * Delete a hub
     * @see [Documentation](https://www.scaleway.com/en/developers/api/iot/#path-iot-hubs-delete-a-hub)
     * @param hubId [Uuid]
     * @param region [IotHubRegion] Default is FR_PAR
     * @return [HttpResponse]
     */
    suspend fun deleteHub(hubId: Uuid, region: IotHubRegion = IotHubClient.DEFAULT_REGION): HttpResponse

    /**
     * Get the certificate authority of a hub
     * @see [Documentation](https://www.scaleway.com/en/developers/api/iot/#path-iot-hubs-get-the-certificate-authority-of-a-hub)
     * @param hubId [Uuid]
     * @param region [IotHubRegion] Default is FR_PAR
     * @return [CertificateAuthority]
     */
    suspend fun getHubCertificateAuthority(hubId: Uuid, region: IotHubRegion = IotHubClient.DEFAULT_REGION): CertificateAuthority

    /**
     * Set the certificate authority of a hub
     * @see [Documentation](https://www.scaleway.com/en/developers/api/iot/#path-iot-hubs-set-the-certificate-authority-of-a-hub)
     * @param hubId [Uuid]
     * @param certificate [SetCertificateAuthorityBody]
     * @param region [IotHubRegion] Default is FR_PAR
     * @return [Hub]
     */
    suspend fun setHubCertificateAuthority(hubId: Uuid, certificate: SetCertificateAuthorityBody, region: IotHubRegion = IotHubClient.DEFAULT_REGION): Hub

    /**
     * Disable a hub
     * @see [Documentation](https://www.scaleway.com/en/developers/api/iot/#path-iot-hubs-disable-a-hub)
     * @param hubId [Uuid]
     * @param region [IotHubRegion] Default is FR_PAR
     * @return [Hub]
     */
    suspend fun disableHub(hubId: Uuid, region: IotHubRegion = IotHubClient.DEFAULT_REGION): Hub

    /**
     * Enable a hub
     * @see [Documentation](https://www.scaleway.com/en/developers/api/iot/#path-iot-hubs-enable-a-hub)
     * @param hubId [Uuid]
     * @param region [IotHubRegion] Default is FR_PAR
     * @return [Hub]
     */
    suspend fun enableHub(hubId: Uuid, region: IotHubRegion = IotHubClient.DEFAULT_REGION): Hub

    /**
     * Get hub metrics
     * @see [Documentation](https://www.scaleway.com/en/developers/api/iot/#path-iot-hubs-get-a-hubs-metrics
     * @param hubId [Uuid]
     * @param startDate: [Instant]
     * @param region [IotHubRegion] Default is FR_PAR
     * @return [Metrics]
     */
    suspend fun getHubMetrics(
        hubId: Uuid,
        startDate: Instant,
        region: IotHubRegion = IotHubClient.DEFAULT_REGION
    ): Metrics


}
