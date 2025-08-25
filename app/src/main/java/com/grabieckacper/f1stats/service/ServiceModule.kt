package com.grabieckacper.f1stats.service

import com.grabieckacper.f1stats.service.impl.RaceServiceImpl
import com.grabieckacper.f1stats.service.impl.StandingsServiceImpl
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
    fun provideStandingsService(httpClient: HttpClient): StandingsService {
        return StandingsServiceImpl(httpClient = httpClient)
    }

    @Provides
    @Singleton
    fun provideRaceService(httpClient: HttpClient): RaceService {
        return RaceServiceImpl(httpClient = httpClient)
    }
}
