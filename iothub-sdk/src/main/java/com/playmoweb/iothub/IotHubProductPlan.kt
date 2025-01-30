package com.playmoweb.iothub

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Possible values
 * plan_unknown
 * plan_shared
 * plan_dedicated
 * plan_ha
 */
@Serializable
enum class IotHubProductPlan(val value: String) {
    @SerialName("plan_unknown")
    UNKNOWN("plan_unknown"),
    @SerialName("plan_shared")
    SHARED("plan_shared"),
    @SerialName("plan_dedicated")
    DEDICATED("plan_dedicated"),
    @SerialName("plan_ha")
    HA("plan_ha"),
}
