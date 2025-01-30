package com.playmoweb.iothub.hub.model

import com.playmoweb.iothub.IotHubProductPlan
import com.playmoweb.iothub.IotHubRegion
import kotlinx.datetime.Instant
import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.uuid.Uuid

/**
 * {
 *       "id": "string",
 *       "name": "string",
 *       "status": "string",
 *       "product_plan": "string",
 *       "enabled": "boolean",
 *       "device_count": "integer",
 *       "connected_device_count": "integer",
 *       "endpoint": "string",
 *       "disable_events": "boolean",
 *       "events_topic_prefix": "string",
 *       "region": "string",
 *       "created_at": "string",
 *       "updated_at": "string",
 *       "project_id": "string",
 *       "organization_id": "string",
 *       "enable_device_auto_provisioning": "boolean",
 *       "has_custom_ca": "boolean",
 *       "twins_graphite_config": {
 *         "push_uri": "string"
 *       }
 *     }
 */
@Serializable
data class Hub(
    @Contextual val id: Uuid,
    val name: String,
    val status: String,
    @SerialName("product_plan") val productPlan: IotHubProductPlan,
    val enabled: Boolean,
    @SerialName("device_count") val deviceCount: Int,
    @SerialName("connected_device_count") val connectedDeviceCount: Int,
    val endpoint: String,
    @SerialName("disable_events") val disableEvents: Boolean,
    @SerialName("events_topic_prefix") val eventsTopicPrefix: String,
    val region: IotHubRegion,
    @SerialName("created_at") val createdAt: Instant,
    @SerialName("updated_at") val updatedAt: Instant,
    @Contextual @SerialName("project_id") val projectId: Uuid,
    @Contextual @SerialName("organization_id") val organizationId: Uuid,
    @SerialName("enable_device_auto_provisioning") val enableDeviceAutoProvisioning: Boolean,
    @SerialName("has_custom_ca") val hasCustomCa: Boolean,
    @SerialName("twins_graphite_config") val twinsGraphiteConfig: TwinsGraphiteConfig? = null
)

@Serializable
data class TwinsGraphiteConfig(
    @SerialName("push_uri") val pushUri: String
)
