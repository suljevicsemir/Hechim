package com.semirsuljevic.foundation.api.storage.preferences

import kotlinx.coroutines.flow.Flow

interface AppPreferences {
    suspend fun getBoolean(key: String): Flow<Boolean?>
    suspend fun setBoolean(key: String, value: Boolean)
}
