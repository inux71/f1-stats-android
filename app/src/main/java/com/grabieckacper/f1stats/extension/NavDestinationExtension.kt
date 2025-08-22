package com.grabieckacper.f1stats.extension

import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import com.grabieckacper.f1stats.common.TabRoute

fun <T: Any> NavDestination?.isSelected(tabRoute: TabRoute<T>): Boolean {
    return this?.hierarchy?.any {
        it.hasRoute(route = tabRoute.route::class)
    } == true
}
