package com.playmoweb.iothub.device

import com.playmoweb.iothub.IotHubRegion
import com.playmoweb.iothub.IotHubSdk
import com.playmoweb.iothub.device.DeviceClient.Companion.MAXIMUM_PAGE_SIZE
import com.playmoweb.iothub.device.model.AddDeviceRequestBody
import com.playmoweb.iothub.device.model.AddDeviceResponse
import com.playmoweb.iothub.device.model.CustomCertificateRequestBody
import com.playmoweb.iothub.device.model.Device
import com.playmoweb.iothub.device.model.DeviceCertificateResponse
import com.playmoweb.iothub.device.model.DeviceOrderBy
import com.playmoweb.iothub.device.model.DeviceStatus
import com.playmoweb.iothub.model.Metrics
import com.playmoweb.iothub.device.model.ListDevicesResponse
import com.playmoweb.iothub.device.model.UpdateDeviceRequestBody
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
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.encodeToJsonElement
import kotlin.uuid.Uuid

class IotHubDeviceClient(
    private val client: HttpClient
) : DeviceClient {

    companion object {
        const val DEVICE_ROUTE_PATH = "/devices"
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
    override suspend fun listDevices(
        page: Int,
        pageSize: Int,
        orderBy: DeviceOrderBy,
        hubId: Uuid?,
        allowInsecure: Boolean?,
        status: DeviceStatus?,
        region: IotHubRegion
    ): ListDevicesResponse = client.get("${region}$DEVICE_ROUTE_PATH") {
        parameter("page", page)
        parameter("page_size", pageSize.coerceAtMost(MAXIMUM_PAGE_SIZE))
        parameter("order_by", orderBy.toString())
        hubId?.let { parameter("hub_id", it) }
        allowInsecure?.let { parameter("allow_insecure", it) }
        status?.let { parameter("status", it.toString()) }
    }.body()

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
    ): AddDeviceResponse = client.post("${region}$DEVICE_ROUTE_PATH") {
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
    ): Device = client.get("${region}$DEVICE_ROUTE_PATH/$deviceId").body()

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
    ): Device = client.patch("${region}$DEVICE_ROUTE_PATH/$deviceId") {
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
    ): HttpResponse = client.delete("${region}$DEVICE_ROUTE_PATH/$deviceId")

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
    ): DeviceCertificateResponse = client.get("${region}$DEVICE_ROUTE_PATH/$deviceId/certificate").body()

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
    ): DeviceCertificateResponse = client.put("${region}$DEVICE_ROUTE_PATH/$deviceId/certificate") {
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
    ): Device = client.post("${region}$DEVICE_ROUTE_PATH/$deviceId/disable").body()

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
    ): Device = client.post("${region}$DEVICE_ROUTE_PATH/$deviceId/enable").body()

    /**
     * Get device metrics
     * @see [Documentation](https://www.scaleway.com/en/developers/api/iot/#path-iot-devices-get-a-devices-metrics)
     * @param deviceId [Uuid]
     * @param startDate: [Instant]
     * @param region [IotHubRegion] Default is FR_PAR
     * @return [Metrics]
     */
    override suspend fun getDeviceMetrics(
        deviceId: Uuid,
        startDate: Instant,
        region: IotHubRegion
    ): Metrics {
        // Convert startDateStr to RFC 3339 format
        val startDateStr = startDate.toString()
        return client.get("${region}$DEVICE_ROUTE_PATH/$deviceId/metrics", {
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
    ): AddDeviceResponse = client.post("${region}$DEVICE_ROUTE_PATH/$deviceId/renew-certificate").body()
}
