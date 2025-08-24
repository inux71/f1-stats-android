package com.grabieckacper.f1stats.model.standings.driver

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DriverStandings(
    @SerialName(value = "drivers_championship")
    val driversChampionship: List<DriverChampionship>
)
