package com.grabieckacper.f1stats.view.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.grabieckacper.f1stats.R
import com.grabieckacper.f1stats.ui.theme.F1StatsTheme
import com.grabieckacper.f1stats.view.settings.component.SettingsOption
import kotlinx.serialization.Serializable

@Serializable
object Settings

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun SettingsView(
    viewModel: SettingsViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit
) {
    val state: SettingsViewModel.SettingsViewModelState = viewModel.state.value

    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(text = stringResource(id = R.string.settings))
            },
            navigationIcon = {
                IconButton(onClick = onNavigateBack) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(id = R.string.go_back)
                    )
                }
            }
        )
    }) { paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(paddingValues = paddingValues),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SettingsOption(title = stringResource(id = R.string.dark_theme)) {
                Switch(
                    checked = state.darkTheme,
                    onCheckedChange = {
                        viewModel.changeTheme(darkTheme = it)
                    },
                    thumbContent = {
                        if (state.darkTheme) {
                            Icon(imageVector = Icons.Default.Check, contentDescription = null)
                        }
                    }
                )
            }

            SettingsOption(title = stringResource(id = R.string.dynamic_color)) {
                Switch(
                    checked = state.dynamicColor,
                    onCheckedChange = {
                        viewModel.enableDynamicColor(dynamicColor = it)
                    },
                    thumbContent = {
                        if (state.darkTheme) {
                            Icon(imageVector = Icons.Default.Check, contentDescription = null)
                        }
                    }
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun SettingsPreview() {
    F1StatsTheme {
        SettingsView(onNavigateBack = {})
    }
}
