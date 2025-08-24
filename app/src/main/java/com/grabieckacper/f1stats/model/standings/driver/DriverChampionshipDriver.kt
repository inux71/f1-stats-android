package com.grabieckacper.f1stats.model.standings.driver

import kotlinx.serialization.Serializable

@Serializable
data class DriverChampionshipDriver(
    val name: String,
    val surname: String,
    val nationality: String,
    val birthday: String,
    val number: Int,
    val shortName: String,
    val url: String
)
