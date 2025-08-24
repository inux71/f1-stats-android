package com.grabieckacper.f1stats.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient {
        return HttpClient(engineFactory = CIO) {
            defaultRequest {
                url(urlString = Url.BASE)
            }

            install(plugin = ContentNegotiation) {
                json(json = Json {
                    ignoreUnknownKeys = true
                })
            }

            expectSuccess = true
        }
    }
}
