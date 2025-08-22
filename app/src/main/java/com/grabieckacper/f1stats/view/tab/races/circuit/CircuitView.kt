package com.grabieckacper.f1stats.view.tab.races.circuit

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.grabieckacper.f1stats.R
import com.grabieckacper.f1stats.ui.theme.F1StatsTheme
import kotlinx.serialization.Serializable

@Serializable
object Circuit

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun CircuitView(onNavigateBack: () -> Unit) {
    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(text = "Circuit View")
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
        Box(modifier = Modifier.padding(paddingValues = paddingValues))
    }
}

@Composable
@Preview(showBackground = true)
fun CircuitPreview() {
    F1StatsTheme {
        CircuitView(onNavigateBack = {})
    }
}