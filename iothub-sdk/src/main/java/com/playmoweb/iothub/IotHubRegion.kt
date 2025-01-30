package com.playmoweb.iothub

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class IotHubRegion(val region: String) {
    @SerialName("fr-par")
    FR_PAR("fr-par"),
}
