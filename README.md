# Scaleway IoT Hub SDK for Kotlin Multiplatform

![Version](https://img.shields.io/badge/version-0.1.0-green)
![JVM](https://img.shields.io/badge/platform-jvm-orange)
![Android](https://img.shields.io/badge/platform-android-orange)
![iOS](https://img.shields.io/badge/platform-ios-orange)

This is an unofficial SDK for the Scaleway IoT Hub API. 

It is written in Kotlin Multiplatform and uses the Ktor client library to interact with the API.

## Supported platforms

- JVM
- Android
- iOS

## Setup

Dependencies are hosted on Maven Central.

### Add dependency

```kotlin
implementation("com.playmoweb.iothub:iothub-sdk:0.1.0")
```

## Usage

### Example

```kotlin
import com.playmoweb.iothub.IotHubConfig
import com.playmoweb.iothub.IotHubSdk

val sdk: IotHubSdk = IotHubSdk(
    iotHubConfig = IotHubConfig(
        accessKey = "your_access_key",
        secretKey = "your_secret_key"
    ),
)

val devices = sdk.client.listDevices()
```

## Features
- [x] Hubs
- [x] Devices
- [x] Routes
- [x] Networks
- [x] Twins (experimental) ([Cloud Twins API is in BETA](https://www.scaleway.com/en/developers/api/iot/#path-iot-cloud-twins-beta-list-the-documents-of-a-cloud-twin))

## Useful links
- [Scaleway IoT Hub API documentation](https://www.scaleway.com/en/developers/api/iot/)
