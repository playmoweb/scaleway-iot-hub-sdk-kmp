package com.playmoweb.iothub.network.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.uuid.Uuid

@Serializable
data class CreateNetworkRequestBody(
    @Contextual @SerialName("hub_id") val hubId: Uuid,
    val name: String,
    @SerialName("topic_prefix") val topicPrefix: String,
    val type: NetworkType,
)
