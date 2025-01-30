package com.playmoweb.iothub.route.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpdateRouteRequestBody(
    val name: String? = null,
    val topic: String? = null,
    @SerialName("s3_config") val s3Config: S3Config? = null,
    @SerialName("db_config") val dbConfig: DbConfig? = null,
    @SerialName("rest_config") val restConfig: RestConfig? = null
)
