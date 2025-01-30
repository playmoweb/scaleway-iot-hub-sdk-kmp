package com.playmoweb.iothub.model.hub

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CertificateAuthority(
    @SerialName("ca_cert_pem") val certificatePem: String,
)
