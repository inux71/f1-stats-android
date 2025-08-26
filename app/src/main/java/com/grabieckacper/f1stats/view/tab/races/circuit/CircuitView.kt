package com.grabieckacper.f1stats.view.tab.races.circuit

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.grabieckacper.f1stats.R
import com.grabieckacper.f1stats.view.component.UrlButton
import kotlinx.serialization.Serializable

@Serializable
data class Circuit(val id: String)

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun CircuitView(
    viewModel: CircuitViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit
) {
    val state: CircuitViewModel.CircuitViewModelState = viewModel.state.value

    val snackbarHostState = remember {
        SnackbarHostState()
    }

    LaunchedEffect(key1 = state.errorMessage) {
        state.errorMessage?.let { errorMessage ->
            snackbarHostState.showSnackbar(message = errorMessage)
            viewModel.setErrorMessage(errorMessage = null)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    state.circuit?.let { circuit ->
                        Text(text = circuit.circuitName)
                    }
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
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier.fillMaxSize()
                .padding(paddingValues = paddingValues),
            contentAlignment = Alignment.Center
        ) {
            state.circuit?.let { circuit ->
                Column(
                    modifier = Modifier.fillMaxSize()
                        .padding(horizontal = 4.dp),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(text = "Country: ${circuit.country}")
                        Text(text = "City: ${circuit.city}")
                        Text(text = "Length: ${circuit.circuitLength} m")
                        Text(text = "Corners: ${circuit.numberOfCorners}")
                    }

                    HorizontalDivider()

                    UrlButton(
                        uriString = circuit.url,
                        text = stringResource(id = R.string.read_more),
                        intentErrorCallback = {
                            viewModel.setErrorMessage(errorMessage = "Unable to open url.")
                        }
                    )
                }
            }

            if (state.isLoading) {
                CircularProgressIndicator()
            }
        }
    }
}
