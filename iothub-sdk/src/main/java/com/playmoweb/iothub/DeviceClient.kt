package com.playmoweb.iothub

import com.playmoweb.iothub.model.AddDeviceRequestBody
import com.playmoweb.iothub.model.AddDeviceResponse
import com.playmoweb.iothub.model.Device
import com.playmoweb.iothub.model.DeviceCertificateResponse
import com.playmoweb.iothub.model.DeviceMetrics
import com.playmoweb.iothub.model.ListDevicesResponse
import com.playmoweb.iothub.model.UpdateDeviceRequestBody
import io.ktor.client.statement.HttpResponse
import kotlinx.datetime.Instant
import kotlin.uuid.Uuid

interface DeviceClient {
    /**
     * Get devices
     * @see [Documentation](https://www.scaleway.com/en/developers/api/iot/#path-iot-devices-list-devices)
     * @return [ListDevicesResponse]
     */
    suspend fun listDevices(
        region: IotHubRegion = IotHubClient.DEFAULT_REGION
    ): ListDevicesResponse

    /**
     * Add a device
     * @see [Documentation](https://www.scaleway.com/en/developers/api/iot/#path-iot-devices-add-a-device)
     * @param deviceToAdd [AddDeviceRequestBody]
     * @param region [IotHubRegion] Default is FR_PAR
     * @return [AddDeviceResponse]
     */
    suspend fun addDevice(
        deviceToAdd: AddDeviceRequestBody,
        region: IotHubRegion = IotHubClient.DEFAULT_REGION,
    ): AddDeviceResponse

    /**
     * Get a device
     * @see [Documentation](https://www.scaleway.com/en/developers/api/iot/#path-iot-devices-get-a-device)
     * @param deviceId [Uuid]
     * @param region [IotHubRegion] Default is FR_PAR
     * @return [Device]
     */
    suspend fun getDevice(
        deviceId: Uuid,
        region: IotHubRegion = IotHubClient.DEFAULT_REGION
    ): Device

    /**
     * Update a device
     * @see [Documentation](https://www.scaleway.com/en/developers/api/iot/#path-iot-devices-update-a-device)
     * @param deviceId [Uuid]
     * @param deviceToUpdate [AddDeviceRequestBody]
     * @param region [IotHubRegion] Default is FR_PAR
     * @return [Device]
     */
    suspend fun updateDevice(
        deviceId: Uuid,
        deviceToUpdate: UpdateDeviceRequestBody,
        region: IotHubRegion = IotHubClient.DEFAULT_REGION
    ): Device

    /**
     * Remove a device
     * @see [Documentation](https://www.scaleway.com/en/developers/api/iot/#path-iot-devices-remove-a-device)
     * @param deviceId [Uuid]
     * @param region [IotHubRegion] Default is FR_PAR
     */
    suspend fun removeDevice(
        deviceId: Uuid,
        region: IotHubRegion = IotHubClient.DEFAULT_REGION
    ): HttpResponse

    /**
     * Get device certificate
     * @see [Documentation](https://www.scaleway.com/en/developers/api/iot/#path-iot-devices-get-a-devices-certificate)
     * @param deviceId [Uuid]
     * @param region [IotHubRegion] Default is FR_PAR
     * @return [DeviceCertificateResponse]
     */
    suspend fun getDeviceCertificate(
        deviceId: Uuid,
        region: IotHubRegion = IotHubClient.DEFAULT_REGION
    ): DeviceCertificateResponse

    /**
     * Set a custom certificate
     * @see [Documentation](https://www.scaleway.com/en/developers/api/iot/#path-iot-devices-set-a-custom-certificate-on-a-device)
     * @param deviceId [Uuid]
     * @param certificatePem [String]
     * @param region [IotHubRegion] Default is FR_PAR
     * @return [DeviceCertificateResponse]
     */
    suspend fun setCustomCertificate(
        deviceId: Uuid,
        certificatePem: String,
        region: IotHubRegion = IotHubClient.DEFAULT_REGION
    ): DeviceCertificateResponse

    /**
     * Disable a device
     * @see [Documentation](https://www.scaleway.com/en/developers/api/iot/#path-iot-devices-disable-a-device)
     * @param deviceId [Uuid]
     * @param region [IotHubRegion] Default is FR_PAR
     * @return [Device]
     */
    suspend fun disableDevice(
        deviceId: Uuid,
        region: IotHubRegion = IotHubClient.DEFAULT_REGION
    ): Device

    /**
     * Enable a device
     * @see [Documentation](https://www.scaleway.com/en/developers/api/iot/#path-iot-devices-enable-a-device)
     * @param deviceId [Uuid]
     * @param region [IotHubRegion] Default is FR_PAR
     * @return [Device]
     */
    suspend fun enableDevice(
        deviceId: Uuid,
        region: IotHubRegion = IotHubClient.DEFAULT_REGION
    ): Device

    /**
     * Get device metrics
     * @see [Documentation](https://www.scaleway.com/en/developers/api/iot/#path-iot-devices-get-a-devices-metrics)
     * @param deviceId [Uuid]
     * @param startDate: [Instant]
     * @param region [IotHubRegion] Default is FR_PAR
     * @return [DeviceMetrics]
     */
    suspend fun getDeviceMetrics(
        deviceId: Uuid,
        startDate: Instant,
        region: IotHubRegion = IotHubClient.DEFAULT_REGION
    ): DeviceMetrics

    /**
     * Renew a device certificate
     * @see [Documentation](https://www.scaleway.com/en/developers/api/iot/#path-iot-devices-renew-a-device-certificate)
     * @param deviceId [Uuid]
     * @param region [IotHubRegion] Default is FR_PAR
     * @return [AddDeviceResponse]
     */
    suspend fun renewDeviceCertificate(
        deviceId: Uuid,
        region: IotHubRegion = IotHubClient.DEFAULT_REGION
    ): AddDeviceResponse
}
