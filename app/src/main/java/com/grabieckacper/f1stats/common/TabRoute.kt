package com.grabieckacper.f1stats.common

import androidx.compose.ui.graphics.vector.ImageVector

data class TabRoute<T: Any>(val name: String, val route: T, val icon: ImageVector)
