package com.grabieckacper.f1stats.service

import com.grabieckacper.f1stats.model.circuit.Circuit

interface CircuitService {
    suspend fun getCircuit(id: String): Circuit
}
