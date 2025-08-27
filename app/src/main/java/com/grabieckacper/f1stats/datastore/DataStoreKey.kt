package com.grabieckacper.f1stats.datastore

import androidx.datastore.preferences.core.booleanPreferencesKey

object DataStoreKey {
    val DARK_THEME = booleanPreferencesKey(name = "dark_theme")
    val DYNAMIC_COLOR = booleanPreferencesKey(name = "dynamic_color")
}
