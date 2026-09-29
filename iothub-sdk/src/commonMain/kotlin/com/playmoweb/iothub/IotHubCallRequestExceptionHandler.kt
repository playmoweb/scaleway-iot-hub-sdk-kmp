package com.playmoweb.iothub

import io.ktor.client.call.body
import io.ktor.client.plugins.CallRequestExceptionHandler
import io.ktor.client.plugins.ResponseException
import io.ktor.client.request.HttpRequest
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import kotlin.coroutines.cancellation.CancellationException
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

val IotHubCallRequestExceptionHandler: CallRequestExceptionHandler = { exception, request ->
    val responseException = exception as? ResponseException
    responseException?.let {
        val exceptionResponse = responseException.response
        throw IotHubException(request, exceptionResponse.status, exceptionResponse.errorBody())
    }
}

/**
 * Reads the Scaleway error body. Falls back to the raw response text when the body is not a Scaleway error
 * (e.g. an HTML page from a proxy), so the original HTTP status is never hidden by a serialization error.
 */
private suspend fun HttpResponse.errorBody(): ErrorBody = try {
    body<ErrorBody>()
} catch (cause: CancellationException) {
    throw cause
} catch (ignored: Exception) {
    ErrorBody(
        message = runCatching { bodyAsText() }.getOrNull()?.takeIf { it.isNotBlank() } ?: status.description,
        type = UNKNOWN_ERROR_TYPE,
    )
}

internal const val UNKNOWN_ERROR_TYPE = "unknown"

open class IotHubException(
    request: HttpRequest,
    val statusCode: HttpStatusCode,
    val body: ErrorBody
): RuntimeException("IotHub error $statusCode: ${body.message}${body.resource?.let { " for $it" } ?: ""}${body.resourceId?.let { " with id $it" } ?: ""} on ${request.method.value} ${request.url}") {

    /** True when the API answered 404 Not Found, e.g. the requested resource does not exist. */
    val isNotFound: Boolean
        get() = statusCode == HttpStatusCode.NotFound
}

@Serializable
data class ErrorBody(
    val message: String,
    val type: String,
    val resource: String? = null,
    @SerialName("resource_id") val resourceId: String? = null,
    val reason: String? = null,
    val method: String? = null
)
