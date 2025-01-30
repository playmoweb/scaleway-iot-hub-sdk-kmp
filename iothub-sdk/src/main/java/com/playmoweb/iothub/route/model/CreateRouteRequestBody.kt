package com.playmoweb.iothub.route.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.uuid.Uuid

@Serializable
data class CreateRouteRequestBody(
    val name: String,
    @Contextual @SerialName("hub_id") val hubId: Uuid,
    val topic: String,
    @SerialName("s3_config") val s3Config: S3Config? = null,
    @SerialName("db_config") val dbConfig: DbConfig? = null,
    @SerialName("rest_config") val restConfig: RestConfig? = null
)
