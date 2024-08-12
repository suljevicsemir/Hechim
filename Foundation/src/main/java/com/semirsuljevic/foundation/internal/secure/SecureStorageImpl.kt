package com.semirsuljevic.foundation.internal.secure

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.semirsuljevic.foundation.api.secure.SecureStorage
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SecureStorageImpl @Inject constructor(
    @ApplicationContext private val applicationContext: Context
): SecureStorage {

    private var sharedPref: SharedPreferences
    init {
        val masterKey = MasterKey.Builder(applicationContext).setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build()
        sharedPref = EncryptedSharedPreferences.create(
            applicationContext,
            "name",
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    override fun getIntValue(key: String, defaultValue: Int):Int {
        return sharedPref.getInt(key, defaultValue)
    }

    override fun storeIntValue(key: String, value: Int) {
        val editor = sharedPref.edit()
        editor.putInt(key, value)
        editor.apply()
    }

    override fun getStringValue(key: String, defaultValue: String?): String? {
        return sharedPref.getString(key, defaultValue)
    }

    override fun storeStringValue(key: String, value: String) {
        val editor = sharedPref.edit()
        editor.putString(key, value)
        editor.apply()
    }

    override fun setBooleanValue(key: String, value: Boolean) {
        val editor = sharedPref.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    override fun getBooleanValue(key: String): Boolean {
        return sharedPref.getBoolean(key, false)
    }

    override fun deleteAll() {
        val editor = sharedPref.edit()
        editor.clear()
        editor.apply()
    }
}
