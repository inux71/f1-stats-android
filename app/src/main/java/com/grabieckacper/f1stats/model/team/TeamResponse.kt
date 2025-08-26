package com.grabieckacper.f1stats.model.team

import kotlinx.serialization.Serializable

@Serializable
data class TeamResponse(val team: List<Team>)
