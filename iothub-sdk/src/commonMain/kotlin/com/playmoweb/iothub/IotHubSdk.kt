package com.playmoweb.iothub

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.contextual
import kotlin.uuid.Uuid

class IotHubSdk(
    iotHubConfig: IotHubConfig,
    json: Json = DEFAULT_JSON,
    debugApiResponse: Boolean = false
) {
    companion object {
        val DEFAULT_JSON = Json {
            ignoreUnknownKeys = true
            encodeDefaults = true
            serializersModule = SerializersModule {
                contextual(Uuid.serializer())
            }
        }
    }

    private val httpClient = HttpClient(CIO) {
        if (debugApiResponse) {
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }
        }
        defaultRequest {
            url(iotHubConfig.apiUrl)
            header("X-Auth-Token", iotHubConfig.secretKey)
            contentType(ContentType.Application.Json)
        }
        install(ContentNegotiation) {
            json(json)
        }
        expectSuccess = true
        HttpResponseValidator {
            handleResponseExceptionWithRequest(IotHubCallRequestExceptionHandler)
        }
    }

    val client: IotHubClient = IotHubClient(
        client = httpClient
    )
}
