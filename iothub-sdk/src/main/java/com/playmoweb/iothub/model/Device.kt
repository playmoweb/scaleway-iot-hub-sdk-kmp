package com.playmoweb.iothub.model

import kotlinx.datetime.Instant
import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.uuid.Uuid

/**
 * {
 *  *       "id": "string",
 *  *       "name": "string",
 *  *       "description": "string",
 *  *       "status": "string",
 *  *       "hub_id": "string",
 *  *       "last_activity_at": "string",
 *  *       "is_connected": "boolean",
 *  *       "allow_insecure": "boolean",
 *  *       "allow_multiple_connections": "boolean",
 *  *       "message_filters": {
 *  *         "publish": {
 *  *           "policy": "string",
 *  *           "topics": [
 *  *             "string"
 *  *           ]
 *  *         },
 *  *         "subscribe": {
 *  *           "policy": "string",
 *  *           "topics": [
 *  *             "string"
 *  *           ]
 *  *         }
 *  *       },
 *  *       "has_custom_certificate": "boolean",
 *  *       "created_at": "string",
 *  *       "updated_at": "string"
 *  *     }
 */
@Serializable
data class Device(
    @Contextual val id: Uuid,
    val name: String,
    val description: String,
    val status: String,
    @SerialName("hub_id") @Contextual val hubId: Uuid,
    @SerialName("last_activity_at") val lastActivityAt: Instant,
    @SerialName("is_connected") val isConnected: Boolean,
    @SerialName("allow_insecure") val allowInsecure: Boolean,
    @SerialName("allow_multiple_connections") val allowMultipleConnections: Boolean,
    @SerialName("message_filters") val messageFilters: MessageFilters,
    @SerialName("has_custom_certificate") val hasCustomCertificate: Boolean,
    @SerialName("created_at") val createdAt: Instant,
    @SerialName("updated_at") val updatedAt: Instant
)
