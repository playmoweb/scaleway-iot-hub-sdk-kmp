package com.playmoweb.iothub.model.device

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.uuid.Uuid

/**
 * {
 *     "allow_insecure": false,
 *     "allow_multiple_connections": false,
 *     "hub_id": "string",
 *     "name": "string"
 *   }
 */
@Serializable
data class AddDeviceRequestBody(
    val name: String,
    @Contextual @SerialName("hub_id") val hubId: Uuid,
    @SerialName("allow_insecure") val allowInsecure: Boolean? = null,
    @SerialName("allow_multiple_connections") val allowMultipleConnections: Boolean? = null,
    @SerialName("message_filters") val messageFilters: MessageFilters? = null,
    val description: String? = null,
)
