package com.grabieckacper.f1stats.model.driver

import kotlinx.serialization.Serializable

@Serializable
data class DriverResponse(val driver: List<Driver>)
