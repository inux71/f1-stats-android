package com.grabieckacper.f1stats.model.standings.constructor

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ConstructorStandings(
    @SerialName(value = "constructors_championship")
    val constructorsChampionship: List<ConstructorChampionship>
)
