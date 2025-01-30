package com.playmoweb.iothub.model.hub

import com.playmoweb.iothub.IotHubProductPlan
import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.uuid.Uuid

/**
 * {
 *     "name": "string",
 *     "product_plan": "plan_shared",
 *     "project_id": "string"
 *   }
 */
@Serializable
data class CreateHubRequestBody(
    val name: String,
    @SerialName("product_plan") val productPlan: IotHubProductPlan,
    @Contextual @SerialName("project_id") val projectId: Uuid
)
