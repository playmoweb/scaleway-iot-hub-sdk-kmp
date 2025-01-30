package com.playmoweb.iothub

import com.playmoweb.iothub.model.Metrics
import com.playmoweb.iothub.model.hub.CertificateAuthority
import com.playmoweb.iothub.model.hub.CreateHubRequestBody
import com.playmoweb.iothub.model.hub.Hub
import com.playmoweb.iothub.model.hub.ListHubsResponse
import com.playmoweb.iothub.model.hub.SetCertificateAuthorityBody
import com.playmoweb.iothub.model.hub.UpdateHubRequestBody
import io.ktor.client.statement.HttpResponse
import kotlinx.datetime.Instant
import kotlin.uuid.Uuid

interface HubClient {
    /**
     * List hubs
     * @see [Documentation](https://www.scaleway.com/en/developers/api/iot/#path-iot-hubs-list-hubs)
     * @param region [IotHubRegion] Default is FR_PAR
     * @return [ListHubsResponse]
     */
    suspend fun listHubs(
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
