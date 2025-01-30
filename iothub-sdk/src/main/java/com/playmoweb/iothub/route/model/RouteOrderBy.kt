package com.playmoweb.iothub.route.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

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
enum class RouteOrderBy(val value: String) {
    @SerialName("name_asc") NAME_ASC("name_asc"),
    @SerialName("name_desc") NAME_DESC("name_desc"),
    @SerialName("hub_id_asc") HUB_ID_ASC("hub_id_asc"),
    @SerialName("hub_id_desc") HUB_ID_DESC("hub_id_desc"),
    @SerialName("type_asc") TYPE_ASC("type_asc"),
    @SerialName("type_desc") TYPE_DESC("type_desc"),
    @SerialName("created_at_asc") CREATED_AT_ASC("created_at_asc"),
    @SerialName("created_at_desc") CREATED_AT_DESC("created_at_desc"),
}
