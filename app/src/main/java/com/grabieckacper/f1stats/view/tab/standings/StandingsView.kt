package com.grabieckacper.f1stats.view.tab.standings

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.grabieckacper.f1stats.ui.theme.F1StatsTheme
import kotlinx.serialization.Serializable

@Serializable
object Standings

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun StandingsView(
    onNavigateToDriverView: () -> Unit,
    onNavigateToTeamView: () -> Unit
) {
    Column {
        Button(onClick = onNavigateToDriverView) {
            Text("Driver View")
        }

        Button(onClick = onNavigateToTeamView) {
            Text(text = "Team View")
        }
    }
}

@Composable
@Preview(showBackground = true)
fun StandingsPreview() {
    F1StatsTheme {
        StandingsView(
            onNavigateToDriverView = {},
            onNavigateToTeamView = {}
        )
    }
}
