package com.grabieckacper.f1stats.model.race

import kotlinx.serialization.Serializable

@Serializable
data class Race(
    val raceId: String,
    val raceName: String,
    val laps: Int,
    val round: Int,
    val circuit: RaceCircuit
)
