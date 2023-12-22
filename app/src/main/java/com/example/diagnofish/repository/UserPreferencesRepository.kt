package com.example.diagnofish.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.MutablePreferences
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

data class UserPreferences(
    val isLoggedIn: Boolean = false,
    val email: String = "",
    val userId: String = "",
    val password: String = "",
    val token: String = ""
)

private const val USER_PREFERENCES_NAME = "user_preferences"
private val KEY_IS_LOGGED_IN = booleanPreferencesKey("key_is_logged_in")
private val KEY_EMAIL = stringPreferencesKey("key_email")
private val KEY_USER_ID = stringPreferencesKey("key_user_id")
private val KEY_PASSWORD = stringPreferencesKey("key_password")

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = USER_PREFERENCES_NAME)

class UserPreferencesRepository(
    private val dataStore: DataStore<Preferences>,
    context: Context
) {
    constructor(context: Context) : this(context.dataStore, context)

    val userPreferencesFlow = dataStore.data

    suspend fun updateIsLoggedIn(isLoggedIn: Boolean) {
        dataStore.edit { preferences ->
            preferences[KEY_IS_LOGGED_IN] = isLoggedIn
        }
    }

    fun isLoggedIn(): Flow<Boolean> {
        return dataStore.data.map { preferences ->
            preferences[KEY_IS_LOGGED_IN] ?: false
        }
    }

    suspend fun updateEmail(email: String) {
        dataStore.edit { preferences ->
            preferences[KEY_EMAIL] = email
        }
    }

    fun getEmail(): Flow<String> {
        return dataStore.data.map { preferences ->
            preferences[KEY_EMAIL] ?: ""
        }
    }

    suspend fun updateUserId(userId: String) {
        dataStore.edit { preferences ->
            preferences[KEY_USER_ID] = userId
        }
    }

    fun getUserId(): Flow<String> {
        return dataStore.data.map { preferences ->
            preferences[KEY_USER_ID] ?: ""
        }
    }

    suspend fun updatePassword(password: String) {
        dataStore.edit { preferences ->
            preferences[KEY_PASSWORD] = password
        }
    }

    fun getPassword(): Flow<String> {
        return dataStore.data.map { preferences ->
            preferences[KEY_PASSWORD] ?: ""
        }
    }

    suspend fun clear() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }
}