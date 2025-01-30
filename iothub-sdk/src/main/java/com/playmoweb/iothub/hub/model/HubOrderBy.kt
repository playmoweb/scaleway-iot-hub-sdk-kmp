package com.playmoweb.iothub.hub.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * name_asc
 * name_desc
 * status_asc
 * status_desc
 * product_plan_asc
 * product_plan_desc
 * created_at_asc
 * created_at_desc
 * updated_at_asc
 * updated_at_desc
 */
@Serializable
enum class HubOrderBy(val value: String) {
    @SerialName("name_asc") NAME_ASC("name_asc"),
    @SerialName("name_desc") NAME_DESC("name_desc"),
    @SerialName("status_asc") STATUS_ASC("status_asc"),
    @SerialName("status_desc") STATUS_DESC("status_desc"),
    @SerialName("product_plan_asc") PRODUCT_PLAN_ASC("product_plan_asc"),
    @SerialName("product_plan_desc") PRODUCT_PLAN_DESC("product_plan_desc"),
    @SerialName("created_at_asc") CREATED_AT_ASC("created_at_asc"),
    @SerialName("created_at_desc") CREATED_AT_DESC("created_at_desc"),
    @SerialName("updated_at_asc") UPDATED_AT_ASC("updated_at_asc"),
    @SerialName("updated_at_desc") UPDATED_AT_DESC("updated_at_desc")
}
