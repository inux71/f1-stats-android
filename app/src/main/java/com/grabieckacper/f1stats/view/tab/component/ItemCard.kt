package com.grabieckacper.f1stats.view.tab.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ItemCard(
    modifier: Modifier = Modifier,
    leadingText: String? = null,
    title: String,
    subtitle: String? = null,
    trailingText: String? = null
) {
    ElevatedCard(modifier = modifier.padding(
        horizontal = 8.dp,
        vertical = 4.dp
    )) {
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(all = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                leadingText?.let { leadingText ->
                    Text(
                        text = leadingText,
                        fontWeight = FontWeight.Bold
                    )
                }

                Column(modifier = Modifier.padding(horizontal = 4.dp)) {
                    Text(
                        text = title,
                        fontSize = 24.sp
                    )

                    subtitle?.let { subtitle ->
                        Text(text = subtitle)
                    }
                }
            }

            trailingText?.let { trailingText ->
                Text(text = trailingText)
            }
        }
    }
}
