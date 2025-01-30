package com.playmoweb.iothub.device.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

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
enum class DeviceOrderBy(val value: String) {
    @SerialName("name_asc") NAME_ASC("name_asc"),
    @SerialName("name_desc") NAME_DESC("name_desc"),
    @SerialName("status_asc") STATUS_ASC("status_asc"),
    @SerialName("status_desc") STATUS_DESC("status_desc"),
    @SerialName("hub_id_asc") HUB_ID_ASC("hub_id_asc"),
    @SerialName("hub_id_desc") HUB_ID_DESC("hub_id_desc"),
    @SerialName("created_at_asc") CREATED_AT_ASC("created_at_asc"),
    @SerialName("created_at_desc") CREATED_AT_DESC("created_at_desc"),
    @SerialName("updated_at_asc") UPDATED_AT_ASC("updated_at_asc"),
    @SerialName("updated_at_desc") UPDATED_AT_DESC("updated_at_desc"),
    @SerialName("allow_insecure_asc") ALLOW_INSECURE_ASC("allow_insecure_asc"),
    @SerialName("allow_insecure_desc") ALLOW_INSECURE_DESC("allow_insecure_desc")
}
