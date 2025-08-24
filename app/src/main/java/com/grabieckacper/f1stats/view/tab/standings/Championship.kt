package com.grabieckacper.f1stats.view.tab.standings

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Construction
import androidx.compose.material.icons.filled.SportsMotorsports
import androidx.compose.ui.graphics.vector.ImageVector

enum class Championship(val text: String, val icon: ImageVector) {
    DRIVER(text = "Driver", icon = Icons.Default.SportsMotorsports),
    CONSTRUCTOR(text = "Constructor", icon = Icons.Default.Construction)
}
