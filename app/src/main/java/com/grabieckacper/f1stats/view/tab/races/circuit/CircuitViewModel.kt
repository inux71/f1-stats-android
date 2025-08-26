package com.grabieckacper.f1stats.view.tab.races.circuit

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.grabieckacper.f1stats.service.CircuitService
import dagger.hilt.android.lifecycle.HiltViewModel
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CircuitViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val circuitService: CircuitService
): ViewModel() {
    private val id: String = savedStateHandle.toRoute<Circuit>().id

    internal data class CircuitViewModelState(
        val isLoading: Boolean = false,
        val errorMessage: String? = null,
        val circuit: com.grabieckacper.f1stats.model.circuit.Circuit? = null
    )

    private val _state = mutableStateOf(CircuitViewModelState())
    internal val state: State<CircuitViewModelState>
        get() = _state

    init {
        fetchCircuit()
    }

    private fun setIsLoading(isLoading: Boolean) {
        _state.value = _state.value.copy(isLoading = isLoading)
    }

    private fun fetchCircuit() {
        viewModelScope.launch {
            try {
                setIsLoading(isLoading = true)
                setErrorMessage(errorMessage = null)

                val circuit: com.grabieckacper.f1stats.model.circuit.Circuit =
                    circuitService.getCircuit(id = id)

                _state.value = _state.value.copy(circuit = circuit)
            } catch (e: RedirectResponseException) {
                Log.e("[ERROR]", e.message)

                setErrorMessage(errorMessage = "Try again later.")
            } catch (e: ClientRequestException) {
                Log.e("[ERROR]", e.message)

                setErrorMessage(errorMessage = "Request failed.")
            } catch (e: ServerResponseException) {
                Log.e("[ERROR]", e.message)

                setErrorMessage(errorMessage = "Unable to connect with the server.")
            } catch (e: Exception) {
                Log.e("[ERROR]", e.message ?: "")

                setErrorMessage(errorMessage = "An unknown error occurs.")
            } finally {
                setIsLoading(isLoading = false)
            }
        }
    }

    fun setErrorMessage(errorMessage: String?) {
        _state.value = _state.value.copy(errorMessage = errorMessage)
    }
}
