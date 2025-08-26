package com.grabieckacper.f1stats.model.circuit

import kotlinx.serialization.Serializable

@Serializable
data class CircuitResponse(val circuit: List<Circuit>)
