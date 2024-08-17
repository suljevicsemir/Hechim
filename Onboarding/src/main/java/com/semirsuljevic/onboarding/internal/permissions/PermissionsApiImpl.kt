package com.semirsuljevic.onboarding.internal.permissions

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.PowerManager
import androidx.core.content.ContextCompat
import com.semirsuljevic.foundation.api.datastore.PermissionsRequestsProvider
import com.semirsuljevic.foundation.api.datastore.model.PermissionRequests
import com.semirsuljevic.onboarding.api.permissions.config.PermissionConstants
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first
import javax.inject.Inject

internal class PermissionsApiImpl @Inject constructor(
    private val permissionRequestsProvider: PermissionsRequestsProvider,
    @ApplicationContext private val applicationContext: Context,
): com.semirsuljevic.onboarding.api.permissions.PermissionsApi {

    private fun permissionCanBeGranted(
        permission: String,
        permissionRequests: PermissionRequests,
    ): Boolean = !(permissionRequests.requests.getOrDefault(permission, 0)
        >= PermissionConstants.maxRequests || permissionGranted(permission))

    override suspend fun checkPermissions(): List<String> {
        val value: MutableList<String> = mutableListOf()
        val permissionRequests = permissionRequestsProvider.getPermissionRequests().first()
        //location for Android 8 and Android 9 and Android 11 and above
        if(Build.VERSION.SDK_INT <= Build.VERSION_CODES.P
            && permissionCanBeGranted(android.Manifest.permission.ACCESS_FINE_LOCATION, permissionRequests)) {
            //custom key is used for Android 8 and 9, because of different translations
            // it's config is PermissionConstants.
            value.add(PermissionConstants.android9LocationPermission)
        }
        else if(Build.VERSION.SDK_INT > Build.VERSION_CODES.Q
            && permissionCanBeGranted(android.Manifest.permission.ACCESS_FINE_LOCATION, permissionRequests)) {
            value.add(android.Manifest.permission.ACCESS_FINE_LOCATION)
        }
        //Android 10
        else if(Build.VERSION.SDK_INT == Build.VERSION_CODES.Q
            && permissionCanBeGranted(android.Manifest.permission.ACCESS_BACKGROUND_LOCATION, permissionRequests)) {
            value.add(android.Manifest.permission.ACCESS_BACKGROUND_LOCATION)
        }

        //Activity recognition for Android 10 and above
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q
            && permissionCanBeGranted(android.Manifest.permission.ACTIVITY_RECOGNITION, permissionRequests)) {
            value.add(android.Manifest.permission.ACTIVITY_RECOGNITION)
        }
        //Bluetooth for Android 12 and above
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
            && permissionCanBeGranted(android.Manifest.permission.BLUETOOTH_CONNECT, permissionRequests)) {
            value.add(android.Manifest.permission.BLUETOOTH_CONNECT)
        }
        //Notifications for Android 13 and above
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU
            && permissionCanBeGranted(android.Manifest.permission.POST_NOTIFICATIONS, permissionRequests)) {
            value.add(android.Manifest.permission.POST_NOTIFICATIONS)
        }
        //Battery optimization for Samsung devices
        if(Build.BRAND.lowercase() != PermissionConstants.samsungManufacturer && !checkBatteryOptimization()) {
            value.add(android.Manifest.permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS)
        }
        return value
    }

    private fun checkBatteryOptimization(): Boolean{
        val powerManager = (applicationContext.getSystemService(Context.POWER_SERVICE) as PowerManager)
        return powerManager.isIgnoringBatteryOptimizations(applicationContext.packageName)
    }

    override fun requestIgnoreBatteryOptimization() {
        TODO("Not yet implemented")
    }

    override fun permissionGranted(permission: String): Boolean = ContextCompat.checkSelfPermission(applicationContext, permission) == PackageManager.PERMISSION_GRANTED
}
