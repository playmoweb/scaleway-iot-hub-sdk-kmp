package com.playmoweb.iothub.network.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.uuid.Uuid

/**
 * {
 *   "network": {
 *     "id": "string",
 *     "name": "string",
 *     "type": "string",
 *     "endpoint": "string",
 *     "hub_id": "string",
 *     "created_at": "string",
 *     "topic_prefix": "string"
 *   },
 *   "secret": "string"
 * }
 */
@Serializable
data class CreateNetworkResponse(
    val network: Network,
    val secret: String
)
