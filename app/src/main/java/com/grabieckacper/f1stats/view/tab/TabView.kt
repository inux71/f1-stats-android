package com.grabieckacper.f1stats.view.tab

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Leaderboard
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.SportsScore
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.grabieckacper.f1stats.R
import com.grabieckacper.f1stats.common.TabRoute
import com.grabieckacper.f1stats.extension.isSelected
import com.grabieckacper.f1stats.ui.theme.F1StatsTheme
import com.grabieckacper.f1stats.view.tab.races.Races
import com.grabieckacper.f1stats.view.tab.races.RacesView
import com.grabieckacper.f1stats.view.tab.standings.Standings
import com.grabieckacper.f1stats.view.tab.standings.StandingsView
import kotlinx.serialization.Serializable

@Serializable
object Tab

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun TabView(
    onNavigateToSettingsView: () -> Unit,
    onNavigateToDriverView: () -> Unit,
    onNavigateToTeamView: () -> Unit,
    onNavigateToCircuitView: () -> Unit
    ) {
    val tabRoutes = listOf(
        TabRoute(
            name = "Standings",
            route = Standings,
            icon = Icons.Default.Leaderboard
        ),
        TabRoute(
            name = "Races",
            route = Races,
            icon = Icons.Default.SportsScore
        )
    )

    val snackbarHostState = remember {
        SnackbarHostState()
    }

    val navHostController = rememberNavController()
    val currentNavBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentDestination: NavDestination? = currentNavBackStackEntry?.destination
    val title: String = tabRoutes.find { tabRoute ->
        currentDestination.isSelected(tabRoute = tabRoute)
    }?.name ?: ""

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = title)
                },
                actions = {
                    IconButton(onClick = onNavigateToSettingsView) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = stringResource(id = R.string.settings)
                        )
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar {
                tabRoutes.forEach { tabRoute ->
                    NavigationBarItem(
                        selected = currentDestination.isSelected(tabRoute = tabRoute),
                        onClick = {
                            navHostController.navigate(route = tabRoute.route) {
                                popUpTo(id = navHostController.graph.findStartDestination().id) {
                                    saveState = true
                                }

                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(imageVector = tabRoute.icon, contentDescription = tabRoute.name)
                        },
                        label = {
                            Text(text = tabRoute.name)
                        }
                    )
                }
            }
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { paddingValues ->
        NavHost(
            navController = navHostController,
            startDestination = Standings,
            modifier = Modifier.padding(paddingValues = paddingValues)
        ) {
            composable<Standings> {
                StandingsView(
                    snackbarHostState = snackbarHostState,
                    onNavigateToDriverView = onNavigateToDriverView,
                    onNavigateToTeamView = onNavigateToTeamView
                )
            }

            composable<Races> {
                RacesView(
                    snackbarHostState = snackbarHostState,
                    onNavigateToCircuitView = onNavigateToCircuitView
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun TabPreview() {
    F1StatsTheme {
        TabView(
            onNavigateToSettingsView = {},
            onNavigateToDriverView = {},
            onNavigateToTeamView = {},
            onNavigateToCircuitView = {}
        )
    }
}