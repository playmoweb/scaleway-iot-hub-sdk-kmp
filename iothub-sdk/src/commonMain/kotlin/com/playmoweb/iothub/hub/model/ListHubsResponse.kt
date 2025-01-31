package com.playmoweb.iothub.hub.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ListHubsResponse(
    @SerialName("total_count")
    val totalCount: Int,
    val hubs: List<Hub>
)
