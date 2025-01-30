package com.playmoweb.iothub.route.model

import kotlinx.datetime.Instant
import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.uuid.Uuid

@Serializable
data class Route(
    @Contextual val id: Uuid,
    val name: String,
    @Contextual @SerialName("hub_id") val hubId: Uuid,
    val topic: String,
    val type: RouteType,
    @SerialName("created_at") val createdAt: Instant,
    @SerialName("s3_config") val s3Config: S3Config? = null,
    @SerialName("db_config") val dbConfig: DbConfig? = null,
    @SerialName("rest_config") val restConfig: RestConfig? = null,
    @SerialName("updated_at") val updatedAt: Instant
)

@Serializable
data class S3Config(
    @SerialName("bucket_region") val bucketRegion: String,
    @SerialName("bucket_name") val bucketName: String,
    @SerialName("object_prefix") val objectPrefix: String,
    val strategy: S3Strategy
)

@Serializable
data class DbConfig(
    val engine: DbEngine,
    val host: String,
    val port: Int,
    val dbname: String,
    val username: String,
    val password: String,
    val query: String
)

@Serializable
data class RestConfig(
    val verb: RestVerb,
    val uri: String,
    val headers: Map<String, String> = emptyMap()
)

/**
 * unknown
 * per_topic
 * per_message
 */
@Serializable
enum class S3Strategy(val value: String) {
    @SerialName("unknown") UNKNOWN("unknown"),
    @SerialName("per_topic") PER_TOPIC("per_topic"),
    @SerialName("per_message") PER_MESSAGE("per_message")
}

/**
 * unknown
 * s3
 * database
 * rest
 */
@Serializable
enum class RouteType(val value: String) {
    @SerialName("unknown") UNKNOWN("unknown"),
    @SerialName("s3") S3("s3"),
    @SerialName("database") DATABASE("database"),
    @SerialName("rest") REST("rest")
}

/**
 * unknown
 * postgresql
 * mysql
 */
@Serializable
enum class DbEngine(val value: String) {
    @SerialName("unknown") UNKNOWN("unknown"),
    @SerialName("postgresql") POSTGRESQL("postgresql"),
    @SerialName("mysql") MYSQL("mysql")
}

/**
 * unknown
 * get
 * post
 * put
 * patch
 * delete
 */
@Serializable
enum class RestVerb(val value: String) {
    @SerialName("unknown") UNKNOWN("unknown"),
    @SerialName("get") GET("get"),
    @SerialName("post") POST("post"),
    @SerialName("put") PUT("put"),
    @SerialName("patch") PATCH("patch"),
    @SerialName("delete") DELETE("delete")
}
