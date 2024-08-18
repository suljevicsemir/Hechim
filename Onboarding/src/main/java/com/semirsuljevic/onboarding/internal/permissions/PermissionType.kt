package com.semirsuljevic.onboarding.internal.permissions

import com.semirsuljevic.onboarding.api.permissions.config.PermissionConstants

enum class PermissionType(val manifestPermission: String) {
    //Location While In Use - Android 11 and above
    LocationWhileInUse(android.Manifest.permission.ACCESS_FINE_LOCATION),
    //Android 10 only
    BackgroundLocation(android.Manifest.permission.ACCESS_BACKGROUND_LOCATION),
    //Android 8 and 9
    Location(PermissionConstants.android9LocationPermission),
    //Android 10 and above
    ActivityRecognition(android.Manifest.permission.ACTIVITY_RECOGNITION),
    //Android 12 and above
    Bluetooth(android.Manifest.permission.BLUETOOTH_CONNECT),
    //Android 13 and above
    Notification(android.Manifest.permission.POST_NOTIFICATIONS),
    //Samsung devices only
    BatteryOptimization(android.Manifest.permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS);

    companion object {
        fun fromManifestPermission(permission: String): PermissionType {
            return PermissionType.entries.find { it.manifestPermission == permission }!!
        }
    }
}
