package com.grabieckacper.f1stats.model.standings.driver

import kotlinx.serialization.Serializable

@Serializable
data class DriverChampionshipTeam(
    val teamId: String,
    val teamName: String,
    val country: String,
    val url: String
)
