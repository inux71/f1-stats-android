package com.grabieckacper.f1stats.model.standings.driver

import kotlinx.serialization.Serializable

@Serializable
data class DriverChampionship(
    val driverId: String,
    val teamId: String,
    val points: Int,
    val position: Int,
    val wins: Int,
    val driver: DriverChampionshipDriver,
    val team: DriverChampionshipTeam
)
