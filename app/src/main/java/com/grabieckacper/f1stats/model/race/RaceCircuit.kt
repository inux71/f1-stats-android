package com.grabieckacper.f1stats.model.race

import kotlinx.serialization.Serializable

@Serializable
data class RaceCircuit(
    val circuitId: String,
    val country: String,
    val city: String
)
