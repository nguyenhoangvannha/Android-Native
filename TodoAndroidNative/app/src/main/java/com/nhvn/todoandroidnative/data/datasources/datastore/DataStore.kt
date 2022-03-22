package com.nhvn.todoandroidnative.data.datasources.datastore

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

private const val USER_PREFERENCES_NAME = "user_preferences"

val Context.dataStore by preferencesDataStore(
    name = USER_PREFERENCES_NAME
)

object PreferencesKeys {
    val DARK_MODE = booleanPreferencesKey("dark_mode")
}

//val userPreferencesFlow: Flow<UserPreferences> = dataStore.data
//    .map { preferences ->
//        // Get our show completed value, defaulting to false if not set:
//        val showCompleted = preferences[PreferencesKeys.SHOW_COMPLETED]?: false
//        UserPreferences(showCompleted)
//    }