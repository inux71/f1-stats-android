package com.grabieckacper.f1stats.view.tab.races

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grabieckacper.f1stats.model.race.Race
import com.grabieckacper.f1stats.service.RaceService
import dagger.hilt.android.lifecycle.HiltViewModel
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RacesViewModel @Inject constructor(private val raceService: RaceService): ViewModel() {
    internal data class RacesViewModelState(
        val isLoading: Boolean = false,
        val errorMessage: String? = null,
        val races: List<Race> = emptyList()
    )

    private val _state = mutableStateOf(value = RacesViewModelState())
    internal val state: State<RacesViewModelState>
        get() = _state

    init {
        fetchRaces()
    }

    private fun setIsLoading(isLoading: Boolean) {
        _state.value = _state.value.copy(isLoading = isLoading)
    }

    private fun fetchRaces() {
        viewModelScope.launch {
            try {
                setIsLoading(isLoading = true)
                setErrorMessage(errorMessage = null)

                val races: List<Race> = raceService.getRaces()

                _state.value = _state.value.copy(races = races)
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
