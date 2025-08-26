package com.grabieckacper.f1stats.service.impl

import com.grabieckacper.f1stats.model.team.Team
import com.grabieckacper.f1stats.model.team.TeamResponse
import com.grabieckacper.f1stats.network.Url
import com.grabieckacper.f1stats.service.TeamService
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import javax.inject.Inject

class TeamServiceImpl @Inject constructor(private val httpClient: HttpClient): TeamService {
    override suspend fun getTeam(id: String): Team {
        val response: TeamResponse = httpClient.get(urlString = Url.Team(id = id).path).body()

        return response.team.first()
    }

}
