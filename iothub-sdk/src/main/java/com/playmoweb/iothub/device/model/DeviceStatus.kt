package com.playmoweb.iothub.device.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * unknown
 * error
 * enabled
 * disabled
 */
@Serializable
enum class DeviceStatus(val value: String) {
    @SerialName("unknown") UNKNOWN("unknown"),
    @SerialName("error") ERROR("error"),
    @SerialName("enabled") ENABLED("enabled"),
    @SerialName("disabled") DISABLED("disabled")
}
