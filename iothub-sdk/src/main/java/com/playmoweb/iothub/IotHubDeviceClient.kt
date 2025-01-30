package com.playmoweb.iothub

import com.playmoweb.iothub.model.AddDeviceRequestBody
import com.playmoweb.iothub.model.AddDeviceResponse
import com.playmoweb.iothub.model.CustomCertificateRequestBody
import com.playmoweb.iothub.model.Device
import com.playmoweb.iothub.model.DeviceCertificateResponse
import com.playmoweb.iothub.model.DeviceMetrics
import com.playmoweb.iothub.model.ListDevicesResponse
import com.playmoweb.iothub.model.UpdateDeviceRequestBody
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.patch
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import kotlinx.datetime.Instant
import kotlin.uuid.Uuid

class IotHubDeviceClient(
    private val client: HttpClient
) : DeviceClient {

    /**
     * Get devices
     * @see [Documentation](https://www.scaleway.com/en/developers/api/iot/#path-iot-devices-list-devices)
     * @return [ListDevicesResponse]
     */
    override suspend fun listDevices(
        region: IotHubRegion
    ): ListDevicesResponse = client.get("${region.region}/devices").body()

    /**
     * Add a device
     * @see [Documentation](https://www.scaleway.com/en/developers/api/iot/#path-iot-devices-add-a-device)
     * @param deviceToAdd [AddDeviceRequestBody]
     * @param region [IotHubRegion] Default is FR_PAR
     * @return [AddDeviceResponse]
     */
    override suspend fun addDevice(
        deviceToAdd: AddDeviceRequestBody,
        region: IotHubRegion,
    ): AddDeviceResponse = client.post("${region.region}/devices") {
        setBody(deviceToAdd)
    }.body()

    /**
     * Get a device
     * @see [Documentation](https://www.scaleway.com/en/developers/api/iot/#path-iot-devices-get-a-device)
     * @param deviceId [Uuid]
     * @param region [IotHubRegion] Default is FR_PAR
     * @return [Device]
     */
    override suspend fun getDevice(
        deviceId: Uuid,
        region: IotHubRegion
    ): Device = client.get("${region.region}/devices/$deviceId").body()

    /**
     * Update a device
     * @see [Documentation](https://www.scaleway.com/en/developers/api/iot/#path-iot-devices-update-a-device)
     * @param deviceId [Uuid]
     * @param deviceToUpdate [AddDeviceRequestBody]
     * @param region [IotHubRegion] Default is FR_PAR
     * @return [Device]
     */
    override suspend fun updateDevice(
        deviceId: Uuid,
        deviceToUpdate: UpdateDeviceRequestBody,
        region: IotHubRegion
    ): Device = client.patch("${region.region}/devices/$deviceId") {
        setBody(deviceToUpdate)
    }.body()

    /**
     * Remove a device
     * @see [Documentation](https://www.scaleway.com/en/developers/api/iot/#path-iot-devices-remove-a-device)
     * @param deviceId [Uuid]
     * @param region [IotHubRegion] Default is FR_PAR
     */
    override suspend fun removeDevice(
        deviceId: Uuid,
        region: IotHubRegion
    ): HttpResponse = client.delete("${region.region}/devices/$deviceId")

    /**
     * Get device certificate
     * @see [Documentation](https://www.scaleway.com/en/developers/api/iot/#path-iot-devices-get-a-devices-certificate)
     * @param deviceId [Uuid]
     * @param region [IotHubRegion] Default is FR_PAR
     * @return [DeviceCertificateResponse]
     */
    override suspend fun getDeviceCertificate(
        deviceId: Uuid,
        region: IotHubRegion
    ): DeviceCertificateResponse = client.get("${region.region}/devices/$deviceId/certificate").body()

    /**
     * Set a custom certificate
     * @see [Documentation](https://www.scaleway.com/en/developers/api/iot/#path-iot-devices-set-a-custom-certificate-on-a-device)
     * @param deviceId [Uuid]
     * @param certificatePem [String]
     * @param region [IotHubRegion] Default is FR_PAR
     * @return [DeviceCertificateResponse]
     */
    override suspend fun setCustomCertificate(
        deviceId: Uuid,
        certificatePem: String,
        region: IotHubRegion
    ): DeviceCertificateResponse = client.put("${region.region}/devices/$deviceId/certificate") {
        setBody(CustomCertificateRequestBody(certificatePem))
    }.body()

    /**
     * Disable a device
     * @see [Documentation](https://www.scaleway.com/en/developers/api/iot/#path-iot-devices-disable-a-device)
     * @param deviceId [Uuid]
     * @param region [IotHubRegion] Default is FR_PAR
     * @return [Device]
     */
    override suspend fun disableDevice(
        deviceId: Uuid,
        region: IotHubRegion
    ): Device = client.post("${region.region}/devices/$deviceId/disable").body()

    /**
     * Enable a device
     * @see [Documentation](https://www.scaleway.com/en/developers/api/iot/#path-iot-devices-enable-a-device)
     * @param deviceId [Uuid]
     * @param region [IotHubRegion] Default is FR_PAR
     * @return [Device]
     */
    override suspend fun enableDevice(
        deviceId: Uuid,
        region: IotHubRegion
    ): Device = client.post("${region.region}/devices/$deviceId/enable").body()

    /**
     * Get device metrics
     * @see [Documentation](https://www.scaleway.com/en/developers/api/iot/#path-iot-devices-get-a-devices-metrics)
     * @param deviceId [Uuid]
     * @param startDate: [Instant]
     * @param region [IotHubRegion] Default is FR_PAR
     * @return [DeviceMetrics]
     */
    override suspend fun getDeviceMetrics(
        deviceId: Uuid,
        startDate: Instant,
        region: IotHubRegion
    ): DeviceMetrics {
        // Convert startDateStr to RFC 3339 format
        val startDateStr = startDate.toString()
        return client.get("${region.region}/devices/$deviceId/metrics", {
            parameter("start_date", startDateStr)
        }).body()
    }

    /**
     * Renew a device certificate
     * @see [Documentation](https://www.scaleway.com/en/developers/api/iot/#path-iot-devices-renew-a-device-certificate)
     * @param deviceId [Uuid]
     * @param region [IotHubRegion] Default is FR_PAR
     * @return [AddDeviceResponse]
     */
    override suspend fun renewDeviceCertificate(
        deviceId: Uuid,
        region: IotHubRegion
    ): AddDeviceResponse = client.post("${region.region}/devices/$deviceId/renew-certificate").body()
}
