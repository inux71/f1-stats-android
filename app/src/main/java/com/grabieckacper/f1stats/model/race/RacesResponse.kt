package com.grabieckacper.f1stats.model.race

import kotlinx.serialization.Serializable

@Serializable
data class RacesResponse(val races: List<Race>)
