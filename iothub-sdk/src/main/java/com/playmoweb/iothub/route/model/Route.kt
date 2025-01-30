package com.playmoweb.iothub.route.model

import com.playmoweb.iothub.IotHubSdk
import kotlinx.datetime.Instant
import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.encodeToJsonElement
import kotlinx.serialization.json.jsonPrimitive
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
enum class S3Strategy {
    @SerialName("unknown") UNKNOWN,
    @SerialName("per_topic") PER_TOPIC,
    @SerialName("per_message") PER_MESSAGE;

    override fun toString(): String = IotHubSdk.DEFAULT_JSON.encodeToJsonElement(this).jsonPrimitive.content
}

/**
 * unknown
 * s3
 * database
 * rest
 */
@Serializable
enum class RouteType {
    @SerialName("unknown") UNKNOWN,
    @SerialName("s3") S3,
    @SerialName("database") DATABASE,
    @SerialName("rest") REST;

    override fun toString(): String = IotHubSdk.DEFAULT_JSON.encodeToJsonElement(this).jsonPrimitive.content
}

/**
 * unknown
 * postgresql
 * mysql
 */
@Serializable
enum class DbEngine {
    @SerialName("unknown") UNKNOWN,
    @SerialName("postgresql") POSTGRESQL,
    @SerialName("mysql") MYSQL;

    override fun toString(): String = IotHubSdk.DEFAULT_JSON.encodeToJsonElement(this).jsonPrimitive.content
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
enum class RestVerb {
    @SerialName("unknown") UNKNOWN,
    @SerialName("get") GET,
    @SerialName("post") POST,
    @SerialName("put") PUT,
    @SerialName("patch") PATCH,
    @SerialName("delete") DELETE;

    override fun toString(): String = IotHubSdk.DEFAULT_JSON.encodeToJsonElement(this).jsonPrimitive.content
}
