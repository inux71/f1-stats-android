package com.grabieckacper.f1stats.service

import com.grabieckacper.f1stats.model.team.Team

interface TeamService {
    suspend fun getTeam(id: String): Team
}
