package com.playmoweb.iothub.device.model

import com.playmoweb.iothub.IotHubSdk
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.encodeToJsonElement
import kotlinx.serialization.json.jsonPrimitive

/**
 * unknown
 * error
 * enabled
 * disabled
 */
@Serializable
enum class DeviceStatus {
    @SerialName("unknown") UNKNOWN,
    @SerialName("error") ERROR,
    @SerialName("enabled") ENABLED,
    @SerialName("disabled") DISABLED;

    override fun toString(): String = IotHubSdk.DEFAULT_JSON.encodeToJsonElement(this).jsonPrimitive.content
}
