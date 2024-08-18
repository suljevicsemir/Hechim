package com.semirsuljevic.onboarding.api.permissions.viewmodel

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.PowerManager
import android.provider.Settings
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import com.semirsuljevic.foundation.api.datastore.model.PermissionRequests
import com.semirsuljevic.onboarding.api.permissions.config.PermissionConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


/**
ViewModel that holds common methods for Permissions and Trapdoor feature.
 */
@HiltViewModel
open class PermissionsProviderViewModel @Inject constructor(
    @ApplicationContext private val applicationContext: Context
): ViewModel(){

    /**
    Checks if permission can be granted - should be displayed in permissions list.
    OS only allows up to 2 requests, requests are stored in DataStore and permission won't be displayed if it
    was asks 2 times already. In addition checks if permissions is already granted.
     * @param permission - manifest permission string
     * @param permissionRequests - PermissionRequests object read from data store which has previous requests stored.
     */
    private fun permissionCanBeGranted(
        permission: String,
        permissionRequests: PermissionRequests,
    ): Boolean = !(permissionRequests.requests.getOrDefault(permission, 0)
        >= PermissionConstants.maxRequests || permissionGranted(permission))

    fun permissionGranted(
        permission: String
    ): Boolean = ContextCompat.checkSelfPermission(applicationContext, permission) == PackageManager.PERMISSION_GRANTED

    fun checkBatteryOptimization(): Boolean{
        val powerManager = (applicationContext.getSystemService(Context.POWER_SERVICE) as PowerManager)
        return powerManager.isIgnoringBatteryOptimizations(applicationContext.packageName)
    }

    fun requestIgnoreBatteryOptimization() {
        val intent = Intent()
        val powerManager = applicationContext.getSystemService(Context.POWER_SERVICE) as PowerManager
        if (!powerManager.isIgnoringBatteryOptimizations(applicationContext.packageName)) {
            intent.setAction(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS)
            intent.setData(Uri.parse("${PermissionConstants.packageConstant}:${applicationContext.packageName}"))
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            applicationContext.startActivity(intent)
        }
    }
    //easier to use if then else statements than when (previously used)
    //we would have to duplicate adding permissions for different android versions (like bluetooth)
    fun checkPermissions(permissionRequests: PermissionRequests): List<String> {
        val value: MutableList<String> = mutableListOf()
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


}
