package com.playmoweb.iothub.model.device

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * {
 *   "total_count": "integer",
 *   "devices": [
 *     {
 *       "id": "string",
 *       "name": "string",
 *       "description": "string",
 *       "status": "string",
 *       "hub_id": "string",
 *       "last_activity_at": "string",
 *       "is_connected": "boolean",
 *       "allow_insecure": "boolean",
 *       "allow_multiple_connections": "boolean",
 *       "message_filters": {
 *         "publish": {
 *           "policy": "string",
 *           "topics": [
 *             "string"
 *           ]
 *         },
 *         "subscribe": {
 *           "policy": "string",
 *           "topics": [
 *             "string"
 *           ]
 *         }
 *       },
 *       "has_custom_certificate": "boolean",
 *       "created_at": "string",
 *       "updated_at": "string"
 *     }
 *   ]
 * }
 */
@Serializable
data class ListDevicesResponse(
    @SerialName("total_count")
    val totalCount: Int,
    val devices: List<Device>
)
