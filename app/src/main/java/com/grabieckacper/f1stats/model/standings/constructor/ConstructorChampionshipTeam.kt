package com.grabieckacper.f1stats.model.standings.constructor

import kotlinx.serialization.Serializable

@Serializable
data class ConstructorChampionshipTeam(
    val teamName: String,
    val country: String,
    val url: String
)
