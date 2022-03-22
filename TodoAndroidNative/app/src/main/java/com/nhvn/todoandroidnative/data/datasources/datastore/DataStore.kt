//package com.nhvn.todoandroidnative.data.datasources.datastore
//
//import android.content.Context
//import androidx.datastore.preferences.core.booleanPreferencesKey
//import androidx.datastore.preferences.preferencesDataStore
//
//data class UserPreferences(val showCompleted: Boolean)
//
//private const val USER_PREFERENCES_NAME = "user_preferences"
//
//private val Context.dataStore by preferencesDataStore(
//    name = USER_PREFERENCES_NAME
//)
//
//private object PreferencesKeys {
//    val SHOW_COMPLETED = booleanPreferencesKey("show_completed")
//}
//
//val userPreferencesFlow: Flow<UserPreferences> = dataStore.data
//    .map { preferences ->
//        // Get our show completed value, defaulting to false if not set:
//        val showCompleted = preferences[PreferencesKeys.SHOW_COMPLETED]?: false
//        UserPreferences(showCompleted)
//    }