package com.playmoweb.iothub.hub.model

import com.playmoweb.iothub.IotHubSdk
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.encodeToJsonElement
import kotlinx.serialization.json.jsonPrimitive

/**
 * Possible values
 * plan_unknown
 * plan_shared
 * plan_dedicated
 * plan_ha
 */
@Serializable
enum class IotHubProductPlan {
    @SerialName("plan_unknown") UNKNOWN,
    @SerialName("plan_shared") SHARED,
    @SerialName("plan_dedicated") DEDICATED,
    @SerialName("plan_ha") HA;

    override fun toString(): String = IotHubSdk.DEFAULT_JSON.encodeToJsonElement(this).jsonPrimitive.content
}
