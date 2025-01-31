package com.playmoweb.iothub

import com.playmoweb.iothub.device.model.DeviceStatus
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlin.test.Test
import kotlin.test.assertEquals

class DeviceStatusSerializationTest {

    @Serializable
    data class DeviceStatusWrapper(
        val status: DeviceStatus
    )

    @Test
    fun deviceStatusDeserializationTest() {
        val wrapped = DeviceStatusWrapper(DeviceStatus.ENABLED)
        val jsonString = IotHubSdk.DEFAULT_JSON.encodeToString(wrapped)
        println(jsonString)
        assertEquals("{\"status\":\"enabled\"}", jsonString)

        val unwrapped: DeviceStatusWrapper = IotHubSdk.DEFAULT_JSON.decodeFromString(jsonString)
        assertEquals(DeviceStatus.ENABLED, unwrapped.status)

        val encodedStatus = DeviceStatus.ENABLED.toString()
        println(encodedStatus)
        assertEquals("enabled", encodedStatus)
    }
}
