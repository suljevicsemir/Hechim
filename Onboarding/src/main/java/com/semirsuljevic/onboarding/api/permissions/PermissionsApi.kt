package com.semirsuljevic.onboarding.api.permissions

interface PermissionsApi {

    suspend fun checkPermissions(): List<String>

    /** Starts Intent for Ignore Battery Optimization Popup */
    fun requestIgnoreBatteryOptimization()

    /** Returns true if permission is given */
    fun permissionGranted(permission: String): Boolean
}
