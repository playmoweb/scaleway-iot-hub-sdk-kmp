package com.playmoweb.iothub

import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.delete
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class IotHubExceptionTest {

    private val deviceUrl = "https://api.scaleway.com/iot/v1/regions/fr-par/devices/0f4d7e9c-5b1a-4c3e-9a2b-8d6f1e0c7a53"

    private fun client(status: HttpStatusCode, body: String, contentType: ContentType) = HttpClient(MockEngine {
        respond(body, status, headersOf(HttpHeaders.ContentType, contentType.toString()))
    }) {
        install(ContentNegotiation) {
            json(IotHubSdk.DEFAULT_JSON)
        }
        expectSuccess = true
        HttpResponseValidator {
            handleResponseExceptionWithRequest(IotHubCallRequestExceptionHandler)
        }
    }

    private fun deleteDeviceFailure(status: HttpStatusCode, body: String, contentType: ContentType = ContentType.Application.Json) =
        assertFailsWith<IotHubException> {
            runBlocking { client(status, body, contentType).delete(deviceUrl) }
        }

    @Test
    fun notFoundExposesStatusAndBody() {
        val exception = deleteDeviceFailure(
            HttpStatusCode.NotFound,
            """{"message":"resource is not found","type":"not_found","resource":"device","resource_id":"0f4d7e9c-5b1a-4c3e-9a2b-8d6f1e0c7a53"}""",
        )

        assertEquals(HttpStatusCode.NotFound, exception.statusCode)
        assertTrue(exception.isNotFound)
        assertEquals("not_found", exception.body.type)
        assertEquals("device", exception.body.resource)
        assertEquals("0f4d7e9c-5b1a-4c3e-9a2b-8d6f1e0c7a53", exception.body.resourceId)
        assertEquals(
            "IotHub error 404 Not Found: resource is not found for device with id 0f4d7e9c-5b1a-4c3e-9a2b-8d6f1e0c7a53 on DELETE $deviceUrl",
            exception.message,
        )
    }

    @Test
    fun forbiddenIsNotNotFound() {
        val exception = deleteDeviceFailure(
            HttpStatusCode.Forbidden,
            """{"message":"insufficient permissions","type":"permissions_denied"}""",
        )

        assertEquals(HttpStatusCode.Forbidden, exception.statusCode)
        assertFalse(exception.isNotFound)
    }

    @Test
    fun serverErrorIsWrappedInIotHubException() {
        val exception = deleteDeviceFailure(
            HttpStatusCode.ServiceUnavailable,
            """{"message":"service is unavailable","type":"service_unavailable"}""",
        )

        assertEquals(HttpStatusCode.ServiceUnavailable, exception.statusCode)
        assertEquals("service_unavailable", exception.body.type)
    }

    @Test
    fun nonScalewayBodyKeepsStatusAndRawText() {
        val exception = deleteDeviceFailure(
            HttpStatusCode.BadGateway,
            "<html>Bad Gateway</html>",
            ContentType.Text.Html,
        )

        assertEquals(HttpStatusCode.BadGateway, exception.statusCode)
        assertEquals(UNKNOWN_ERROR_TYPE, exception.body.type)
        assertEquals("<html>Bad Gateway</html>", exception.body.message)
    }
}
