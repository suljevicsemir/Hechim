package com.semirsuljevic.foundation.api.secure

interface SecureStorage {
    companion object {
        const val accessTokenKey = "accessToken"
        const val isLoggedInKey = "isLoggedIn"
        const val userId = "userId"
        const val biometricsSet = "biometricsSet"
        const val locale = "locale"
    }

    fun deleteAll()

    fun getIntValue(key: String, defaultValue: Int = 0): Int
    fun storeIntValue(key: String, value: Int)

    fun getStringValue(key: String, defaultValue: String? = null): String?
    fun storeStringValue(key: String, value: String)

    fun setBooleanValue(key: String, value: Boolean)
    fun getBooleanValue(key: String): Boolean

}
