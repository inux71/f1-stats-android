package com.grabieckacper.f1stats.ui.theme

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
class ThemeViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
): ViewModel() {
    internal data class ThemeViewModelState(
        val darkTheme: Boolean = false,
        val dynamicColor: Boolean = false
    )

    private val _state = mutableStateOf(ThemeViewModelState())
    internal val state: State<ThemeViewModelState>
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
}
