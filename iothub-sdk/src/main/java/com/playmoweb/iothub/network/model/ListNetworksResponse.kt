package com.playmoweb.iothub.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ListNetworksResponse(
    @SerialName("total_count")
    val totalCount: Int,
    val networks: List<Network>
)
