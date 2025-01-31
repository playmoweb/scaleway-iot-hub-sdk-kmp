import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import com.vanniktech.maven.publish.KotlinMultiplatform
import com.vanniktech.maven.publish.SonatypeHost

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.maven.publish)
}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }
    jvm()
    iosArm64()
    iosX64()
    iosSimulatorArm64()

    sourceSets {
        configureEach {
            languageSettings {
                optIn("kotlin.uuid.ExperimentalUuidApi")
                optIn("kotlinx.serialization.ExperimentalSerializationApi")
            }
        }

        commonMain {
            dependencies {
                implementation(libs.kotlinx.datetime)
                implementation(libs.ktor.client.content.negotiation)
                implementation(libs.ktor.client.core)
                implementation(libs.ktor.client.logging)
                implementation(libs.ktor.client.serialization.kotlinx.json)
            }
        }

        androidMain {
            dependencies {
                implementation(libs.ktor.client.cio)
            }
        }

        iosMain {
            dependencies {
                implementation(libs.ktor.client.darwin)
            }
        }

        jvmMain {
            dependencies {
                implementation(libs.ktor.client.cio)
            }
        }

        commonTest {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }

        jvmTest {
            dependencies {
                implementation(libs.kotlin.test.junit)
            }
        }
    }
}

android {
    namespace = "com.playmoweb.iothub"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

mavenPublishing {
    configure(
        KotlinMultiplatform()
    )
    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)
    signAllPublications()
    coordinates("com.playmoweb.iothub", "iothub-sdk", "0.1.0")
    pom {
        name.set("scaleway-iot-hub-sdk-kmp")
        description.set("A Kotlin Multiplatform SDK for interacting with Scaleway IoT Hub API.")
        inceptionYear.set("2025")
        url.set("https://github.com/username/mylibrary/")
        licenses {
            license {
                name.set("The Apache License, Version 2.0")
                url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                distribution.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
            }
        }
        developers {
            developer {
                id.set("playmoweb")
                name.set("Playmoweb")
                url.set("https://github.com/playmoweb/")
            }
        }
        scm {
            url.set("https://github.com/playmoweb/scaleway-iot-hub-sdk-kmp/")
            connection.set("scm:git:git://github.com/playmoweb/scaleway-iot-hub-sdk-kmp.git")
            developerConnection.set("scm:git:ssh://git@github.com/playmoweb/scaleway-iot-hub-sdk-kmp.git")
        }
    }
}
