package com.playmoweb.iothub

import io.ktor.client.call.body
import io.ktor.client.plugins.CallRequestExceptionHandler
import io.ktor.client.plugins.ClientRequestException
import io.ktor.http.HttpStatusCode
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

val IotHubCallRequestExceptionHandler: CallRequestExceptionHandler = { exception, _ ->
    val clientException = exception as? ClientRequestException
    clientException?.let {
        val exceptionResponse = clientException.response
        throw IotHubException(exceptionResponse.status, exceptionResponse.body())
    }
}

open class IotHubException(
    statusCode: HttpStatusCode,
    body: ErrorBody
): RuntimeException("IotHub error $statusCode: ${body.message}${body.resource?.let { " for $it" }}${body.resourceId?.let { " with id $it" }}")

@Serializable
data class ErrorBody(
    val message: String,
    val type: String,
    val resource: String? = null,
    @SerialName("resource_id") val resourceId: String? = null,
    val reason: String? = null,
    val method: String? = null
)
