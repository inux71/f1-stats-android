package com.grabieckacper.f1stats.service.impl

import com.grabieckacper.f1stats.model.circuit.Circuit
import com.grabieckacper.f1stats.model.circuit.CircuitResponse
import com.grabieckacper.f1stats.network.Url
import com.grabieckacper.f1stats.service.CircuitService
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import javax.inject.Inject

class CircuitServiceImpl @Inject constructor(private val httpClient: HttpClient): CircuitService {
    override suspend fun getCircuit(id: String): Circuit {
        val response: CircuitResponse = httpClient.get(urlString = Url.Circuit(id = id).path).body()

        return response.circuit.first()
    }
}
