package com.grabieckacper.f1stats.view.settings.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.grabieckacper.f1stats.ui.theme.F1StatsTheme

@Composable
fun SettingsOption(
    title: String,
    content: @Composable (RowScope.() -> Unit)
) {
    Row(
        modifier = Modifier.fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = title)

        content()
    }
}

@Composable
@Preview(showBackground = true)
fun SettingsOptionPreview() {
    F1StatsTheme {
        SettingsOption(title = "Dark theme") {
            Switch(
                checked = true,
                onCheckedChange = {}
            )
        }
    }
}
