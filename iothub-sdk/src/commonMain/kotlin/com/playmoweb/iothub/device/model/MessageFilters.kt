package com.playmoweb.iothub.device.model

import kotlinx.serialization.Serializable

/**
 * {
 *  *  *         "publish": {
 *  *  *           "policy": "string",
 *  *  *           "topics": [
 *  *  *             "string"
 *  *  *           ]
 *  *  *         },
 *  *  *         "subscribe": {
 *  *  *           "policy": "string",
 *  *  *           "topics": [
 *  *  *             "string"
 *  *  *           ]
 *  *  *         }
 *  *  *       }
 */
@Serializable
data class MessageFilters(
    val publish: MessageFilter,
    val subscribe: MessageFilter
)

@Serializable
data class MessageFilter(
    val policy: String,
    val topics: List<String>
)
