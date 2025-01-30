package com.playmoweb.iothub.model.device

import kotlinx.serialization.Serializable

@Serializable
data class Certificate(
    val crt: String,
    val key: String
)
