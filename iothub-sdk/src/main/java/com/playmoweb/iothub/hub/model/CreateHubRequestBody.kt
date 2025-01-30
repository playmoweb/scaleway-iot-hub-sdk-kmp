package com.playmoweb.iothub.hub.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.uuid.Uuid

@Serializable
data class CreateHubRequestBody(
    val name: String,
    @SerialName("product_plan") val productPlan: IotHubProductPlan,
    @Contextual @SerialName("project_id") val projectId: Uuid,
    @SerialName("disable_events") val disableEvents: Boolean? = null,
    @SerialName("events_topic_prefix") val eventsTopicPrefix: String? = null,
    @SerialName("twins_graphite_config") val twinsGraphiteConfig: TwinsGraphiteConfig? = null
)
