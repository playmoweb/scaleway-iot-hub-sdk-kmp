package com.playmoweb.iothub

import io.ktor.client.call.body
import io.ktor.client.plugins.CallRequestExceptionHandler
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.request.HttpRequest
import io.ktor.http.HttpStatusCode
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

val IotHubCallRequestExceptionHandler: CallRequestExceptionHandler = { exception, request ->
    val clientException = exception as? ClientRequestException
    clientException?.let {
        val exceptionResponse = clientException.response
        throw IotHubException(request, exceptionResponse.status, exceptionResponse.body())
    }
}

open class IotHubException(
    request: HttpRequest,
    statusCode: HttpStatusCode,
    body: ErrorBody
): RuntimeException("IotHub error $statusCode: ${body.message}${body.resource?.let { " for $it" } ?: ""}${body.resourceId?.let { " with id $it" } ?: ""} on ${request.method.value} ${request.url}")

@Serializable
data class ErrorBody(
    val message: String,
    val type: String,
    val resource: String? = null,
    @SerialName("resource_id") val resourceId: String? = null,
    val reason: String? = null,
    val method: String? = null
)
