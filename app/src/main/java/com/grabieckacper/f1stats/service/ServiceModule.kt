package com.grabieckacper.f1stats.service

import com.grabieckacper.f1stats.service.impl.CircuitServiceImpl
import com.grabieckacper.f1stats.service.impl.DriverServiceImpl
import com.grabieckacper.f1stats.service.impl.RaceServiceImpl
import com.grabieckacper.f1stats.service.impl.StandingsServiceImpl
import com.grabieckacper.f1stats.service.impl.TeamServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    @Provides
    @Singleton
    fun provideCircuitService(httpClient: HttpClient): CircuitService {
        return CircuitServiceImpl(httpClient = httpClient)
    }

    @Provides
    @Singleton
    fun provideDriverService(httpClient: HttpClient): DriverService {
        return DriverServiceImpl(httpClient = httpClient)
    }

    @Provides
    @Singleton
    fun provideRaceService(httpClient: HttpClient): RaceService {
        return RaceServiceImpl(httpClient = httpClient)
    }

    @Provides
    @Singleton
    fun provideStandingsService(httpClient: HttpClient): StandingsService {
        return StandingsServiceImpl(httpClient = httpClient)
    }

    @Provides
    @Singleton
    fun provideTeamService(httpClient: HttpClient): TeamService {
        return TeamServiceImpl(httpClient = httpClient)
    }
}
