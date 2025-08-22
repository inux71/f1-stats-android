package com.grabieckacper.f1stats.view.tab.races

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.grabieckacper.f1stats.ui.theme.F1StatsTheme
import kotlinx.serialization.Serializable

@Serializable
object Races

@Composable
fun RacesView(onNavigateToCircuitView: () -> Unit) {
        Button(onClick = onNavigateToCircuitView) {
            Text(text = "Circuit View")
        }
}

@Composable
@Preview(showBackground = true)
fun RacesPreview() {
    F1StatsTheme {
        RacesView(onNavigateToCircuitView = {})
    }
}
