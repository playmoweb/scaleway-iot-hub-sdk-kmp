package com.playmoweb.iothub.twin.model

import com.playmoweb.iothub.twin.ExperimentalTwin
import kotlinx.serialization.Serializable

/**
 * TODO Missing data
 */
@ExperimentalTwin
@Serializable
data class UpdateTwinDocumentRequestBody(
    /**
     * Version of the document to update. If set, ensures that the current version of the document matches before persisting the update.
     */
    val version: Int? = null,
    /**
     * Data of the new document. New data that will replace the contents of the document.
     * "data": {
     *     "<fieldKey>": null
     *   }
     * TODO Missing data
     */
)
