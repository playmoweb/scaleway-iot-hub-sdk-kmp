package com.playmoweb.iothub.model.hub

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SetCertificateAuthorityBody(
    @SerialName("ca_cert_pem") val certificatePem: String,
    @SerialName("challenge_cert_pem") val challengePem: String,
)
