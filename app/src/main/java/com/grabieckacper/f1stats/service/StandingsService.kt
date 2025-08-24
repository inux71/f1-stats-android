package com.grabieckacper.f1stats.service

import com.grabieckacper.f1stats.model.standings.constructor.ConstructorChampionship
import com.grabieckacper.f1stats.model.standings.driver.DriverChampionship

interface StandingsService {
    suspend fun getConstructors(): List<ConstructorChampionship>
    suspend fun getDrivers(): List<DriverChampionship>
}
