package com.playmoweb.iothub

data class IotHubConfig(
    val apiUrl: String = "https://api.scaleway.com/iot/v1/regions/",
    val accessKey: String,
    val secretKey: String
)
