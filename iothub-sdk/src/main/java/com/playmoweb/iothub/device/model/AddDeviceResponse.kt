package com.playmoweb.iothub.device.model

import kotlinx.serialization.Serializable

/**
 * {
 *   "device": {
 *     "id": "string",
 *     "name": "string",
 *     "description": "string",
 *     "status": "string",
 *     "hub_id": "string",
 *     "last_activity_at": "string",
 *     "is_connected": "boolean",
 *     "allow_insecure": "boolean",
 *     "allow_multiple_connections": "boolean",
 *     "message_filters": {
 *       "publish": {
 *         "policy": "string",
 *         "topics": [
 *           "string"
 *         ]
 *       },
 *       "subscribe": {
 *         "policy": "string",
 *         "topics": [
 *           "string"
 *         ]
 *       }
 *     },
 *     "has_custom_certificate": "boolean",
 *     "created_at": "string",
 *     "updated_at": "string"
 *   },
 *   "certificate": {
 *     "crt": "string",
 *     "key": "string"
 *   }
 * }
 */
@Serializable
data class AddDeviceResponse(
    val device: Device,
    val certificate: Certificate
)
