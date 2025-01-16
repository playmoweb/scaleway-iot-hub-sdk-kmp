package com.playmoweb.iothub.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CustomCertificateRequestBody(
    @SerialName("certificate_pem") val certificatePem: String,
)
