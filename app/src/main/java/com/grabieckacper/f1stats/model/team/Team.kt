package com.grabieckacper.f1stats.model.team

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Team(
    val teamId: String,
    val teamName: String,
    val teamNationality: String,
    @SerialName(value = "firstAppeareance") val firstAppearance: Int,
    val constructorsChampionships: Int,
    val driversChampionships: Int,
    val url: String
)
