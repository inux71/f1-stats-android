package com.grabieckacper.f1stats.view.tab.standings.details.team

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.grabieckacper.f1stats.service.TeamService
import dagger.hilt.android.lifecycle.HiltViewModel
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeamViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val teamService: TeamService
): ViewModel() {
    private val id: String = savedStateHandle.toRoute<Team>().id

    internal data class TeamViewModelState(
        val isLoading: Boolean = false,
        val errorMessage: String? = null,
        val team: com.grabieckacper.f1stats.model.team.Team? = null
    )

    private val _state = mutableStateOf(TeamViewModelState())
    internal val state: State<TeamViewModelState>
        get() = _state

    init {
        fetchTeam()
    }

    private fun setIsLoading(isLoading: Boolean) {
        _state.value = _state.value.copy(isLoading = isLoading)
    }

    private fun fetchTeam() {
        viewModelScope.launch {
            try {
                setIsLoading(isLoading = true)
                setErrorMessage(errorMessage = null)

                val team: com.grabieckacper.f1stats.model.team.Team = teamService.getTeam(id = id)

                _state.value = _state.value.copy(team = team)
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
