package com.grabieckacper.f1stats.service.impl

import com.grabieckacper.f1stats.model.driver.Driver
import com.grabieckacper.f1stats.model.driver.DriverResponse
import com.grabieckacper.f1stats.network.Url
import com.grabieckacper.f1stats.service.DriverService
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import javax.inject.Inject

class DriverServiceImpl @Inject constructor(private val httpClient: HttpClient): DriverService {
    override suspend fun getDriver(id: String): Driver {
        val response: DriverResponse = httpClient.get(urlString = Url.Driver(id = id).path).body()

        return response.driver.first()
    }
}
