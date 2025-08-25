package com.grabieckacper.f1stats.view.tab.races

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.grabieckacper.f1stats.view.tab.component.ItemCard
import kotlinx.serialization.Serializable

@Serializable
object Races

@Composable
fun RacesView(
    viewModel: RacesViewModel = hiltViewModel(),
    snackbarHostState: SnackbarHostState,
    onNavigateToCircuitView: () -> Unit
) {
    val state: RacesViewModel.RacesViewModelState = viewModel.state.value

    LaunchedEffect(key1 = state.errorMessage) {
        state.errorMessage?.let { errorMessage ->
            snackbarHostState.showSnackbar(message = errorMessage)
            viewModel.setErrorMessage(errorMessage = null)
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(items = state.races) { race ->
                ItemCard(
                    leadingText = "#${race.round}",
                    title = race.raceName,
                    subtitle = "${race.circuit.city} ${race.circuit.country}"
                )
            }
        }

        if (state.isLoading) {
            CircularProgressIndicator()
        }
    }
}
