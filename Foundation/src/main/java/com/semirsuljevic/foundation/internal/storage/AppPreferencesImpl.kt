package com.semirsuljevic.foundation.internal.storage

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.semirsuljevic.foundation.api.storage.preferences.AppPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class AppPreferencesImpl @Inject constructor(
    val context: Context
): AppPreferences {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "hechim_preferences")
    override suspend fun getBoolean(key: String): Flow<Boolean?> = getDataStoreValue(key)

    override suspend fun setBoolean(key: String, value: Boolean) {
        setDataStoreValue(key, value)
    }

    private suspend inline fun <reified T> setDataStoreValue(key: String, value: T) {
        context.dataStore.edit { preferences ->
            when (T::class) {
                String::class -> preferences[stringPreferencesKey(key)] = value as String
                Int::class -> preferences[intPreferencesKey(key)] = value as Int
                Boolean::class -> preferences[booleanPreferencesKey(key)] = value as Boolean
                Float::class -> preferences[floatPreferencesKey(key)] = value as Float
                Long::class -> preferences[longPreferencesKey(key)] = value as Long
                else -> throw IllegalArgumentException("Unsupported type")
            }
        }
    }

    private inline fun <reified T> getDataStoreValue(key: String): Flow<T> {
        return context.dataStore.data.map { preferences ->
            when (T::class) {
                String::class -> preferences[stringPreferencesKey(key)] as T
                Int::class -> preferences[intPreferencesKey(key)] as T
                Boolean::class -> preferences[booleanPreferencesKey(key)] as T
                Float::class -> preferences[floatPreferencesKey(key)] as T
                Long::class -> preferences[longPreferencesKey(key)] as T
                else -> throw IllegalArgumentException("Unsupported type")
            }
        }
    }
}
