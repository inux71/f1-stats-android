package com.grabieckacper.f1stats.service.impl

import com.grabieckacper.f1stats.model.standings.constructor.ConstructorChampionship
import com.grabieckacper.f1stats.model.standings.constructor.ConstructorStandings
import com.grabieckacper.f1stats.model.standings.driver.DriverChampionship
import com.grabieckacper.f1stats.model.standings.driver.DriverStandings
import com.grabieckacper.f1stats.network.Url
import com.grabieckacper.f1stats.service.StandingsService
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import javax.inject.Inject

class StandingsServiceImpl @Inject constructor(
    private val httpClient: HttpClient
) : StandingsService {
    override suspend fun getConstructors(): List<ConstructorChampionship> {
        val response: ConstructorStandings = httpClient
            .get(urlString = Url.ConstructorStandings.path)
            .body()

        return response.constructorsChampionship
    }

    override suspend fun getDrivers(): List<DriverChampionship> {
        val response: DriverStandings = httpClient.get(urlString = Url.DriverStandings.path).body()

        return response.driversChampionship
    }
}
