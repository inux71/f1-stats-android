package com.grabieckacper.f1stats.view.tab.standings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import com.grabieckacper.f1stats.R
import com.grabieckacper.f1stats.view.tab.standings.component.StandingsCard
import kotlinx.serialization.Serializable

@Serializable
object Standings

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun StandingsView(
    viewModel: StandingsViewModel = hiltViewModel(),
    snackbarHostState: SnackbarHostState,
    onNavigateToDriverView: () -> Unit,
    onNavigateToTeamView: () -> Unit
) {
    val state: StandingsViewModel.StandingsViewModelState = viewModel.state.value

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
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            PrimaryTabRow(selectedTabIndex = state.selectedChampionship.ordinal) {
                Championship.entries.forEachIndexed { index, championship ->
                    Tab(
                        selected = state.selectedChampionship.ordinal == index,
                        onClick = {
                            viewModel.changeChampionship(championship = championship)
                        },
                        text = {
                            Text(text = championship.text)
                        },
                        icon = {
                            Icon(imageVector = championship.icon, contentDescription = null)
                        }
                    )
                }
            }

            /*SearchBar(
                inputField = {
                    SearchBarDefaults.InputField(
                        query = state.searchBarQuery,
                        onQueryChange = {
                            viewModel.updateSearchBarQuery(query = it)
                        },
                        onSearch = {

                        },
                        expanded = state.searchBarExpanded,
                        onExpandedChange = {
                            viewModel.setSearchBarExpanded(expanded = it)
                        },
                        placeholder = {
                            Text(text = stringResource(id = R.string.search))
                        },
                        leadingIcon = {
                            if (state.searchBarExpanded) {
                                IconButton(onClick = {
                                    viewModel.setSearchBarExpanded(expanded = false)
                                }) {
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Default.ArrowBack,
                                        contentDescription = null
                                    )
                                }
                            } else {
                                Icon(imageVector = Icons.Default.Search, contentDescription = null)
                            }
                        },
                        trailingIcon = {
                            if (state.searchBarQuery.isNotEmpty()) {
                                IconButton(onClick = {
                                    viewModel.updateSearchBarQuery(query = "")
                                }) {
                                    Icon(
                                        imageVector = Icons.Default.Clear,
                                        contentDescription = null
                                    )
                                }
                            }
                        }
                    )
                },
                expanded = state.searchBarExpanded,
                onExpandedChange = {
                    viewModel.setSearchBarExpanded(expanded = it)
                }
            ) {

            }*/

            LazyColumn(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                when (state.selectedChampionship) {
                    Championship.DRIVER -> {
                        items(items = state.driversChampionship) { driverChampionship ->
                            StandingsCard(
                                leadingText = "#${driverChampionship.position}",
                                title = "${driverChampionship.driver.name} ${driverChampionship.driver.surname}",
                                subtitle = driverChampionship.team.teamName,
                                trailingText = "${driverChampionship.points}"
                            )
                        }
                    }

                    Championship.CONSTRUCTOR -> {
                        items(items = state.constructorsChampionship) { constructorChampionship ->
                            StandingsCard(
                                leadingText = "#${constructorChampionship.position}",
                                title = constructorChampionship.team.teamName,
                                subtitle = constructorChampionship.team.country,
                                trailingText = "${constructorChampionship.points}"
                            )
                        }
                    }
                }
            }
        }

        if (state.isLoading) {
            CircularProgressIndicator()
        }
    }
}
