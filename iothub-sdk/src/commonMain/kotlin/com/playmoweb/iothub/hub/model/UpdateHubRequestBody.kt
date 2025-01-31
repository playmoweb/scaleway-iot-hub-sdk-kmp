package com.playmoweb.iothub.hub.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpdateHubRequestBody(
    val name: String? = null,
    @SerialName("product_plan") val productPlan: IotHubProductPlan? = null,
    @SerialName("disable_events") val disableEvents: Boolean? = null,
    @SerialName("events_topic_prefix") val eventsTopicPrefix: String? = null,
    @SerialName("enable_device_auto_provisioning") val enableDeviceAutoProvisioning: Boolean? = null,
    @SerialName("twins_graphite_config") val twinsGraphiteConfig: TwinsGraphiteConfig? = null,
)
