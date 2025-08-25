package com.grabieckacper.f1stats.service.impl

import com.grabieckacper.f1stats.model.race.Race
import com.grabieckacper.f1stats.model.race.RacesResponse
import com.grabieckacper.f1stats.network.Url
import com.grabieckacper.f1stats.service.RaceService
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import javax.inject.Inject

class RaceServiceImpl @Inject constructor(private val httpClient: HttpClient): RaceService {
    override suspend fun getRaces(): List<Race> {
        val response: RacesResponse = httpClient.get(urlString = Url.RACES).body()

        return response.races
    }
}
