# Scaleway IoT Hub SDK for Java

This is an unofficial SDK for the Scaleway IoT Hub API. 

It is written in Kotlin and uses the Ktor client library to interact with the API.


## Usage

```kotlin
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
- [ ] Routes (not implemented yet)
- [ ] Networks (not implemented yet)
- [ ] Twins (not implemented yet)

## Useful links
- [Scaleway IoT Hub API documentation](https://www.scaleway.com/en/developers/api/iot/)
