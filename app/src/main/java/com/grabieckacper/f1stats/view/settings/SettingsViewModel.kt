package com.grabieckacper.f1stats.view.settings

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grabieckacper.f1stats.datastore.DataStoreKey
import com.grabieckacper.f1stats.repository.DataStoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
): ViewModel() {
    internal data class SettingsViewModelState(
        val darkTheme: Boolean = false,
        val dynamicColor: Boolean = false
    )

    private val _state = mutableStateOf(SettingsViewModelState())
    internal val state: State<SettingsViewModelState>
        get() = _state

    init {
        readDynamicColor()
        readTheme()
    }

    private fun readDynamicColor() {
        viewModelScope.launch {
            dataStoreRepository.read(key = DataStoreKey.DYNAMIC_COLOR, defaultValue = false)
                .collect { dynamicColor ->
                    _state.value = _state.value.copy(dynamicColor = dynamicColor)
                }
        }
    }

    private fun readTheme() {
        viewModelScope.launch {
            dataStoreRepository.read(key = DataStoreKey.DARK_THEME, defaultValue = false)
                .collect { darkTheme ->
                    _state.value = _state.value.copy(darkTheme = darkTheme)
                }
        }
    }

    fun changeTheme(darkTheme: Boolean) {
        _state.value = _state.value.copy(darkTheme = darkTheme)

        viewModelScope.launch {
            dataStoreRepository.write(key = DataStoreKey.DARK_THEME, value = darkTheme)
        }
    }

    fun enableDynamicColor(dynamicColor: Boolean) {
        _state.value = _state.value.copy(dynamicColor = dynamicColor)

        viewModelScope.launch {
            dataStoreRepository.write(key = DataStoreKey.DYNAMIC_COLOR, value = dynamicColor)
        }
    }
}
