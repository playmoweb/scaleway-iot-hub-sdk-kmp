package com.playmoweb.iothub.device.model

import com.playmoweb.iothub.IotHubSdk
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.encodeToJsonElement
import kotlinx.serialization.json.jsonPrimitive

/**
 * name_asc
 * name_desc
 * status_asc
 * status_desc
 * hub_id_asc
 * hub_id_desc
 * created_at_asc
 * created_at_desc
 * updated_at_asc
 * updated_at_desc
 * allow_insecure_asc
 * allow_insecure_desc
 */
@Serializable
enum class DeviceOrderBy {
    @SerialName("name_asc") NAME_ASC,
    @SerialName("name_desc") NAME_DESC,
    @SerialName("status_asc") STATUS_ASC,
    @SerialName("status_desc") STATUS_DESC,
    @SerialName("hub_id_asc") HUB_ID_ASC,
    @SerialName("hub_id_desc") HUB_ID_DESC,
    @SerialName("created_at_asc") CREATED_AT_ASC,
    @SerialName("created_at_desc") CREATED_AT_DESC,
    @SerialName("updated_at_asc") UPDATED_AT_ASC,
    @SerialName("updated_at_desc") UPDATED_AT_DESC,
    @SerialName("allow_insecure_asc") ALLOW_INSECURE_ASC,
    @SerialName("allow_insecure_desc") ALLOW_INSECURE_DESC;

    override fun toString(): String = IotHubSdk.DEFAULT_JSON.encodeToJsonElement(this).jsonPrimitive.content
}
