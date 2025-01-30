package com.playmoweb.iothub.model

import kotlinx.serialization.Serializable

@Serializable
data class Metrics(
    val metrics: List<Metric>
)

/**
 * {"name":"messages_count", "points":[["2024-01-01T00:00:00Z", 0], ["2024-02-01T00:00:00Z", 0]], "metadata":{}}]
 * TODO add points
 */
@Serializable
data class Metric(
    val name: String,
    val metadata: Map<String, String>
)
