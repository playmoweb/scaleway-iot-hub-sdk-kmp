package com.playmoweb.iothub.network.model

import kotlinx.datetime.Instant
import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.uuid.Uuid

/**
 * {
 *       "id": "string",
 *       "name": "string",
 *       "type": "string",
 *       "endpoint": "string",
 *       "hub_id": "string",
 *       "created_at": "string",
 *       "topic_prefix": "string"
 *     }
 */
@Serializable
data class Network(
    @Contextual val id: Uuid,
    val name: String,
    val type: NetworkType,
    val endpoint: String,
    @Contextual @SerialName("hub_id") val hubId: Uuid,
    @SerialName("created_at") val createdAt: Instant,
    @SerialName("topic_prefix") val topicPrefix: String
)

/**
 * unknown
 * sigfox
 * rest
 */
@Serializable
enum class NetworkType(val value: String) {
    @SerialName("unknown") UNKNOWN("unknown"),
    @SerialName("sigfox") SIGFOX("sigfox"),
    @SerialName("rest") REST("rest")
}
