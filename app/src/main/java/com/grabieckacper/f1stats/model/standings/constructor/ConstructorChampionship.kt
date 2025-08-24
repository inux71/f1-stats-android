package com.grabieckacper.f1stats.model.standings.constructor

import kotlinx.serialization.Serializable

@Serializable
data class ConstructorChampionship(
    val teamId: String,
    val points: Int,
    val position: Int,
    val wins: Int,
    val team: ConstructorChampionshipTeam
)
