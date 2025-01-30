package com.playmoweb.iothub.model.device

import kotlinx.serialization.Serializable

@Serializable
data class DeviceMetrics(
    val metrics: List<DeviceMetric>
)

/**
 * {"name":"messages_count", "points":[["2024-01-01T00:00:00Z", 0], ["2024-02-01T00:00:00Z", 0]], "metadata":{}}]
 * TODO add points
 */
@Serializable
data class DeviceMetric(
    val name: String,
    val metadata: Map<String, String>
)
