package com.playmoweb.iothub.model.device

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.uuid.Uuid

@Serializable
data class UpdateDeviceRequestBody(
    val description: String? = null,
    @SerialName("allow_insecure") val allowInsecure: Boolean? = null,
    @SerialName("allow_multiple_connections") val allowMultipleConnections: Boolean? = null,
    @SerialName("message_filters") val messageFilters: MessageFilters? = null,
    @Contextual @SerialName("hub_id") val hubId: Uuid? = null,
)
