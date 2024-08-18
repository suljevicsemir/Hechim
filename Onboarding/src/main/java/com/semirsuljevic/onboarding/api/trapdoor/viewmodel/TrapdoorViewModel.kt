package com.semirsuljevic.onboarding.api.trapdoor.viewmodel

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.result.ActivityResult
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.semirsuljevic.foundation.api.authentication.HechimAuthentication
import com.semirsuljevic.foundation.api.datastore.PermissionsRequestsProvider
import com.semirsuljevic.onboarding.api.permissions.config.PermissionConstants
import com.semirsuljevic.onboarding.api.permissions.viewmodel.PermissionsProviderViewModel
import com.semirsuljevic.onboarding.api.trapdoor.config.TrapdoorConfig
import com.semirsuljevic.onboarding.api.trapdoor.config.TrapdoorConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
ViewModel responsible of trapdoor screens.
 * @property checkTrapdoorPermission - main method responsible for pushing trapdoor screens
 * @property fixPermission - navigates user to the permission solution
 * @property trapdoorConfig - controls the modal state
 */

@HiltViewModel
class TrapdoorViewModel @Inject constructor(
    private val authenticationApi: HechimAuthentication,
    private val permissionRequestsProvider: PermissionsRequestsProvider,
    @ApplicationContext private val applicationContext: Context
): PermissionsProviderViewModel(applicationContext = applicationContext){

    private val _trapdoorConfig = mutableStateOf<TrapdoorConfig?>(null)
    val trapdoorConfig get() = _trapdoorConfig.value

    /**
    Checks necessary permissions on every app launch or every time app is brought to foreground.
    It is invoked from a Lifecycle observer Composable's onResume event (which covers app start as well)
    Order of show priority: Location - Battery Optimization - Physical Activity
    Reversed order of checking as the last satisfied condition will be shown.
    Will be called when user returns from app settings to app - and refresh modal (or close it).
     */
    fun checkTrapdoorPermission() {
        //no need to check for trapdoors if user isn't authenticated
        //this prevents modal invocation for onboarding / auth flow
        //in order for trapdoor modal to kick in
        val value: MutableList<String> = mutableListOf()
        //set to null each time method is triggered - enables to refresh the latest trapdoor value
        var tempConfig : TrapdoorConfig? = null

        viewModelScope.launch {
            val requests = permissionRequestsProvider.getPermissionRequests().first()
            if (!requests.finishedPermissions || !authenticationApi.isAuthenticated()) {
                return@launch
            }
            //Activity recognition for Android 10 and above
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q &&
                !permissionGranted(android.Manifest.permission.ACTIVITY_RECOGNITION)) {
                value.add(android.Manifest.permission.ACTIVITY_RECOGNITION)
                tempConfig = TrapdoorConstants.activityRecognition
            }
            //Battery Optimization for Samsung devices
            if(!checkBatteryOptimization()) {
                value.add(android.Manifest.permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS)
                tempConfig = TrapdoorConstants.batteryOptimization
            }
            //Android 9 and below location
            if(Build.VERSION.SDK_INT <= Build.VERSION_CODES.P &&
                !permissionGranted(android.Manifest.permission.ACCESS_FINE_LOCATION)) {
                value.add(android.Manifest.permission.ACCESS_FINE_LOCATION)
                tempConfig = TrapdoorConstants.location
            }
            //Background location for Android 10 and above
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q &&
                !permissionGranted(android.Manifest.permission.ACCESS_BACKGROUND_LOCATION)) {
                value.add(android.Manifest.permission.ACCESS_BACKGROUND_LOCATION)
                tempConfig = TrapdoorConstants.locationAlways
            }
            _trapdoorConfig.value = tempConfig
        }
    }

    /**
    Invoked from button on Trapdoor screen, if the trapdoor config is for battery optimization,
    request for ignore is invoked.
    Otherwise redirects the user to app settings.
     */
    fun fixPermission(launcher: ManagedActivityResultLauncher<Intent, ActivityResult>) {
        if(_trapdoorConfig.value != null){
            if(_trapdoorConfig.value?.manifestPermission == android
                    .Manifest.permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS) {
                requestIgnoreBatteryOptimization()
                return
            }
            //redirect the user to app settings
            launcher.launch(
                Intent(
                    Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                    Uri.fromParts(TrapdoorConstants.packageConstant, applicationContext.packageName, null)
                )
            )
        }
    }
}
