package com.grabieckacper.f1stats.service

import com.grabieckacper.f1stats.model.driver.Driver

interface DriverService {
    suspend fun getDriver(id: String): Driver
}
