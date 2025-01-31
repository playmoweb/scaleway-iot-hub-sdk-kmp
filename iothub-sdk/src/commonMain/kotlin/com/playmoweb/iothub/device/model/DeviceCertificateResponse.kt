package com.playmoweb.iothub.device.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DeviceCertificateResponse(
    val device: Device,
    @SerialName("certificate_pem") val certificatePem: String
)
