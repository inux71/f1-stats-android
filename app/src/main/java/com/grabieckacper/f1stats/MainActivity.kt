package com.grabieckacper.f1stats

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.grabieckacper.f1stats.ui.theme.F1StatsTheme
import com.grabieckacper.f1stats.view.settings.Settings
import com.grabieckacper.f1stats.view.settings.SettingsView
import com.grabieckacper.f1stats.view.tab.Tab
import com.grabieckacper.f1stats.view.tab.TabView
import com.grabieckacper.f1stats.view.tab.races.circuit.Circuit
import com.grabieckacper.f1stats.view.tab.races.circuit.CircuitView
import com.grabieckacper.f1stats.view.tab.standings.details.driver.Driver
import com.grabieckacper.f1stats.view.tab.standings.details.driver.DriverView
import com.grabieckacper.f1stats.view.tab.standings.details.team.Team
import com.grabieckacper.f1stats.view.tab.standings.details.team.TeamView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.Serializable

@Serializable
object Main

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            F1StatsTheme {
                val navHostController = rememberNavController()

                NavHost(navController = navHostController, startDestination = Main) {
                    navigation<Main>(startDestination = Tab) {
                        composable<Tab> {
                            TabView(
                                onNavigateToSettingsView = {
                                    navHostController.navigate(route = Settings)
                                },
                                onNavigateToDriverView = {
                                    navHostController.navigate(route = Driver)
                                },
                                onNavigateToTeamView = {
                                    navHostController.navigate(route = Team)
                                },
                                onNavigateToCircuitView = {
                                    navHostController.navigate(route = Circuit)
                                }
                            )
                        }

                        composable<Driver> {
                            DriverView(onNavigateBack = {
                                navHostController.popBackStack(route = Tab, inclusive = false)
                            })
                        }

                        composable<Team> {
                            TeamView(onNavigateBack = {
                                navHostController.popBackStack(route = Tab, inclusive = false)
                            })
                        }

                        composable<Circuit> {
                            CircuitView(onNavigateBack = {
                                navHostController.popBackStack(route = Tab, inclusive = false)
                            })
                        }
                    }

                    composable<Settings> {
                        SettingsView(onNavigateBack = {
                            navHostController.popBackStack(route = Tab, inclusive = false)
                        })
                    }
                }
            }
        }
    }
}
