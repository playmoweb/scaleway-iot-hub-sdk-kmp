package com.playmoweb.iothub.device

import com.playmoweb.iothub.IotHubClient
import com.playmoweb.iothub.IotHubRegion
import com.playmoweb.iothub.device.model.AddDeviceRequestBody
import com.playmoweb.iothub.device.model.AddDeviceResponse
import com.playmoweb.iothub.device.model.Device
import com.playmoweb.iothub.device.model.DeviceCertificateResponse
import com.playmoweb.iothub.device.model.DeviceOrderBy
import com.playmoweb.iothub.device.model.DeviceStatus
import com.playmoweb.iothub.device.model.ListDevicesResponse
import com.playmoweb.iothub.device.model.UpdateDeviceRequestBody
import com.playmoweb.iothub.model.Metrics
import io.ktor.client.statement.HttpResponse
import kotlinx.datetime.Instant
import kotlin.uuid.Uuid

interface DeviceClient {

    companion object {
        const val MAXIMUM_PAGE_SIZE = 100
    }

    /**
     * List devices
     * @see [Documentation](https://www.scaleway.com/en/developers/api/iot/#path-iot-devices-list-devices)
     * @param page [Int] Default is 1
     * @param pageSize [Int] Default is 100, Maximum is 100
     * @param orderBy [DeviceOrderBy] Default is NAME_ASC
     * @param hubId [Uuid] Default is null
     * @param allowInsecure [Boolean] Default is null
     * @param status [DeviceStatus] Default is null
     * @param region [IotHubRegion] Default is FR_PAR
     * @return [ListDevicesResponse]
     */
    suspend fun listDevices(
        page: Int = 1,
        pageSize: Int = MAXIMUM_PAGE_SIZE,
        orderBy: DeviceOrderBy = DeviceOrderBy.NAME_ASC,
        hubId: Uuid? = null,
        allowInsecure: Boolean? = null,
        status: DeviceStatus? = null,
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
     * @return [Metrics]
     */
    suspend fun getDeviceMetrics(
        deviceId: Uuid,
        startDate: Instant,
        region: IotHubRegion = IotHubClient.DEFAULT_REGION
    ): Metrics

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
