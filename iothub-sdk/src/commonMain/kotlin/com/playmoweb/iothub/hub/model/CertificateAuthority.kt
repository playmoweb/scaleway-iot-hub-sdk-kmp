package com.playmoweb.iothub.hub.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CertificateAuthority(
    @SerialName("ca_cert_pem") val certificatePem: String,
)
