package com.grabieckacper.f1stats.view.tab.standings.details.driver

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
data class Driver(val id: String)

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun DriverView(
    viewModel: DriverViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit
) {
    val state: DriverViewModel.DriverViewModelState = viewModel.state.value

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
                    state.driver?.let { driver ->
                        Text(text = "${driver.name} ${driver.surname}")
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
            state.driver?.let { driver ->
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
                        Text(text = "Short name: ${driver.shortName}")
                        Text(text = "Number: ${driver.number}")
                        Text(text = "Nationality: ${driver.nationality}")
                        Text(text = "Birthday: ${driver.birthday}")
                    }

                    HorizontalDivider()

                    UrlButton(
                        uriString = driver.url,
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
