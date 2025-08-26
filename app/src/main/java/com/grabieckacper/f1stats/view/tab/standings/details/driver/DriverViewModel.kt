package com.grabieckacper.f1stats.view.tab.standings.details.driver

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.grabieckacper.f1stats.service.DriverService
import dagger.hilt.android.lifecycle.HiltViewModel
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DriverViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val driverService: DriverService
): ViewModel() {
    private val id: String = savedStateHandle.toRoute<Driver>().id

    internal data class DriverViewModelState(
        val isLoading: Boolean = false,
        val errorMessage: String? = null,
        val driver: com.grabieckacper.f1stats.model.driver.Driver? = null
    )

    private val _state = mutableStateOf(DriverViewModelState())
    internal val state: State<DriverViewModelState>
        get() = _state

    init {
        fetchDriver()
    }

    private fun setIsLoading(isLoading: Boolean) {
        _state.value = _state.value.copy(isLoading = isLoading)
    }

    private fun fetchDriver() {
        viewModelScope.launch {
            try {
                setIsLoading(isLoading = true)
                setErrorMessage(errorMessage = null)

                val driver: com.grabieckacper.f1stats.model.driver.Driver = driverService.getDriver(id = id)

                _state.value = _state.value.copy(driver = driver)
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
