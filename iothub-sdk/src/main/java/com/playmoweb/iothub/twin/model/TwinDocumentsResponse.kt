package com.playmoweb.iothub.twin.model

import com.playmoweb.iothub.twin.ExperimentalTwin
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * {
 *   "documents": [
 *     {
 *       "document_name": "string"
 *     }
 *   ]
 * }
 */
@ExperimentalTwin
@Serializable
data class TwinDocumentsResponse(
    val documents: List<TwinDocumentItem>
)

@ExperimentalTwin
@Serializable
data class TwinDocumentItem(
    @SerialName("document_name") val documentName: String
)
