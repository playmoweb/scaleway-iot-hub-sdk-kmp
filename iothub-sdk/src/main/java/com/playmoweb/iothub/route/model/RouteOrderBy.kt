package com.playmoweb.iothub.route.model

import com.playmoweb.iothub.IotHubSdk
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.encodeToJsonElement
import kotlinx.serialization.json.jsonPrimitive

/**
 * name_asc
 * name_desc
 * hub_id_asc
 * hub_id_desc
 * type_asc
 * type_desc
 * created_at_asc
 * created_at_desc
 */
@Serializable
enum class RouteOrderBy {
    @SerialName("name_asc") NAME_ASC,
    @SerialName("name_desc") NAME_DESC,
    @SerialName("hub_id_asc") HUB_ID_ASC,
    @SerialName("hub_id_desc") HUB_ID_DESC,
    @SerialName("type_asc") TYPE_ASC,
    @SerialName("type_desc") TYPE_DESC,
    @SerialName("created_at_asc") CREATED_AT_ASC,
    @SerialName("created_at_desc") CREATED_AT_DESC;

    override fun toString(): String = IotHubSdk.DEFAULT_JSON.encodeToJsonElement(this).jsonPrimitive.content
}
