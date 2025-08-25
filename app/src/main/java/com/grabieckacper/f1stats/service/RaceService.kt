package com.grabieckacper.f1stats.service

import com.grabieckacper.f1stats.model.race.Race

interface RaceService {
    suspend fun getRaces(): List<Race>
}
