package com.grabieckacper.f1stats.model.circuit

import kotlinx.serialization.Serializable

@Serializable
data class Circuit(
    val circuitId: String,
    val circuitName: String,
    val country: String,
    val city: String,
    val circuitLength: Int,
    val numberOfCorners: Int,
    val url: String
)
