package com.playmoweb.iothub.route.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ListRoutesResponse(
    @SerialName("total_count")
    val totalCount: Int,
    val routes: List<Route>
)
