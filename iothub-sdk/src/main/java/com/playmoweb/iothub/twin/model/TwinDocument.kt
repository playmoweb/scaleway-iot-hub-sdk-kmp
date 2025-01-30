package com.playmoweb.iothub.twin.model

import com.playmoweb.iothub.twin.ExperimentalTwin
import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.uuid.Uuid

/**
 * {
 *   "twin_id": "string",
 *   "document_name": "string",
 *   "version": "integer",
 *   "data": {
 *     "<fieldKey>": null
 *   }
 * }
 * TODO Missing data
 */
@ExperimentalTwin
@Serializable
data class TwinDocument(
    @Contextual @SerialName("twin_id") val twinId: Uuid,
    @SerialName("document_name") val documentName: String,
    val version: Int
)
