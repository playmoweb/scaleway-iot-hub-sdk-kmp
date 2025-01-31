package com.playmoweb.iothub.device.model

import kotlinx.serialization.Serializable

@Serializable
data class Certificate(
    val crt: String,
    val key: String
)
