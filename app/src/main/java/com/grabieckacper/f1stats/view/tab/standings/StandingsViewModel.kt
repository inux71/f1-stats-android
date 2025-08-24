package com.grabieckacper.f1stats.view.tab.standings

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grabieckacper.f1stats.model.standings.constructor.ConstructorChampionship
import com.grabieckacper.f1stats.model.standings.driver.DriverChampionship
import com.grabieckacper.f1stats.service.StandingsService
import dagger.hilt.android.lifecycle.HiltViewModel
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StandingsViewModel @Inject constructor(
    private val standingsService: StandingsService
) : ViewModel() {
    internal data class StandingsViewModelState(
        val selectedChampionship: Championship = Championship.DRIVER,
        val searchBarExpanded: Boolean = false,
        val searchBarQuery: String = "",
        val isLoading: Boolean = false,
        val errorMessage: String? = null,
        val constructorsChampionship: List<ConstructorChampionship> = emptyList(),
        val driversChampionship: List<DriverChampionship> = emptyList()
    )

    private val _state = mutableStateOf(value = StandingsViewModelState())
    internal val state: State<StandingsViewModelState>
        get() = _state

    init {
        fetchConstructors()
        fetchDrivers()
    }

    private fun setIsLoading(isLoading: Boolean) {
        _state.value = _state.value.copy(isLoading = isLoading)
    }

    private fun fetchConstructors() {
        viewModelScope.launch {
            try {
                setIsLoading(isLoading = true)
                setErrorMessage(errorMessage = null)

                val constructorsChampionship: List<ConstructorChampionship> =
                    standingsService.getConstructors()

                _state.value = _state.value.copy(constructorsChampionship = constructorsChampionship)
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

    private fun fetchDrivers() {
        viewModelScope.launch {
            try {
                setIsLoading(isLoading = true)
                setErrorMessage(errorMessage = null)

                val driversChampionship: List<DriverChampionship> = standingsService.getDrivers()

                _state.value = _state.value.copy(driversChampionship = driversChampionship)
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

    fun changeChampionship(championship: Championship) {
        _state.value = _state.value.copy(selectedChampionship = championship)
    }

    fun setSearchBarExpanded(expanded: Boolean) {
        _state.value = _state.value.copy(searchBarExpanded = expanded)
    }

    fun updateSearchBarQuery(query: String) {
        _state.value = _state.value.copy(searchBarQuery = query)
    }

    fun setErrorMessage(errorMessage: String?) {
        _state.value = _state.value.copy(errorMessage = errorMessage)
    }
}
