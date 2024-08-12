package com.semirsuljevic.onboarding.api.permissions.viewmodel

import android.content.Context
import android.os.Build
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.lifecycle.viewModelScope
import com.hechimdemo.onboarding.R
import com.semirsuljevic.foundation.api.authentication.HechimAuthentication
import com.semirsuljevic.foundation.api.common.UiText
import com.semirsuljevic.foundation.api.datastore.PermissionsRequestsProvider
import com.semirsuljevic.foundation.api.datastore.model.PermissionRequests
import com.semirsuljevic.onboarding.api.permissions.config.PermissionConfig
import com.semirsuljevic.onboarding.api.permissions.config.PermissionString
import com.semirsuljevic.onboarding.api.permissions.navigation.PermissionsNavigator
import com.semirsuljevic.ui.api.theme.HechimTheme
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PermissionViewModel @Inject constructor(
    private val permissionNavigator: PermissionsNavigator,
    private val permissionRequestsProvider: PermissionsRequestsProvider,
    private val authentication: HechimAuthentication,
    @ApplicationContext private val applicationContext: Context
): PermissionsProviderViewModel(applicationContext = applicationContext){

    //tracks state of description modal sheet
    private val _sheetVisible: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val sheetVisible: StateFlow<Boolean> = _sheetVisible

    fun setSheetVisibility() {
        _sheetVisible.value = !_sheetVisible.value
    }

    //tracks if dialog is visible (for Android 11 background location)
    private val _dialogVisible: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val dialogVisible : StateFlow<Boolean> = _dialogVisible

    fun hideDialog() {
        _dialogVisible.value = false
        viewModelScope.launch {
            permissionNavigator.next()
        }
    }

    //tracks if request ignore battery optimization is prompted to user
    //necessary as battery optimization doesn't behave as other permissions
    private val _batteryOptimizationPrompted = MutableStateFlow(false)
    fun resetBatteryOptimizationPrompted(value: Boolean) {
        _batteryOptimizationPrompted.value = value
    }

    /**
        checks which permissions can be granted for underlying Android version
        sends them to PermissionNavigator to initiate the permissions flow
     */
    fun listPermissions() {
        viewModelScope.launch {
            val permissionRequests: PermissionRequests = permissionRequestsProvider.getPermissionRequests().first()
            val value: List<String> = checkPermissions(permissionRequests)
            permissionNavigator.setRoutes(value)
        }
    }

    /**
        Checks permissions for users which are authenticated but haven't completed the permissions flow.
        This should be checked on app start.
     */
    fun startPermissions() {
        viewModelScope.launch {
            val permissionRequests: PermissionRequests = permissionRequestsProvider.getPermissionRequests().first()
            if(!permissionRequests.finishedPermissions && authentication.isAuthenticated()) {
                val value: List<String> = checkPermissions(permissionRequests)
                permissionNavigator.setRoutes(value)
            }
        }
    }

    fun onNextButtonTapped(
        launcher: ManagedActivityResultLauncher<String, Boolean>,
        manifestPermission: String,
        multipleLauncher: ManagedActivityResultLauncher<Array<String>, Map<String, Boolean>>,
        multiplePermissions: List<String> ? = null
    ) {
        if(multiplePermissions != null) {
            requestMultiplePermission(multipleLauncher, multiplePermissions.toTypedArray())
            return
        }
        if(manifestPermission == android.Manifest.permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS) {
            _batteryOptimizationPrompted.value = true
            requestIgnoreBatteryOptimization()
            return
        }
        requestPermission(manifestPermission, launcher)
    }

    private fun requestMultiplePermission(
        multipleLauncher: ManagedActivityResultLauncher<Array<String>, Map<String, Boolean>>,
        permissions: Array<String>,
    ) {
        viewModelScope.launch {
            recordRequestLaunch(permissions.first())
            multipleLauncher.launch(permissions)
        }
    }

    private suspend fun recordRequestLaunch(permission: String) {
        val permissionRequests = permissionRequestsProvider.getPermissionRequests().first()
        val mappedRequests = permissionRequests.requests.toMutableMap()
        if(mappedRequests.getOrDefault(permission, 0) == 0) {
            mappedRequests[permission] = 1
        }
        else {
            mappedRequests[permission] = 2
        }
        permissionRequestsProvider.updatePermissionRequests(PermissionRequests(
            mappedRequests, permissionRequests.finishedPermissions))
    }

    fun requestPermission(
        permission: String,
        launcher: ManagedActivityResultLauncher<String, Boolean>
    ) {
        viewModelScope.launch {
            recordRequestLaunch(permission)
            launcher.launch(permission)
        }
    }


    fun onPermissionGranted(config: PermissionConfig) {
        //while in use location for Android 11 and above
        //ACCESS_FINE_LOCATION is for precise location, so if user selects 'approximate' in the sys popup
        //this will not be triggered
        if(config.manifestPermission == android.Manifest.permission.ACCESS_FINE_LOCATION
            && Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            _dialogVisible.value = true
            return
        }
        viewModelScope.launch {
            permissionNavigator.next()
        }

    }

    /**
    When app is usable again check if current permission is BatteryOptimization.
    If true and control flag for battery optimization is true it means battery optimization popup is dismissed.
    Whatever the output of dismiss is, navigate to next route.
     */
    fun onResume(config: PermissionConfig) {
        viewModelScope.launch {
            if(config.manifestPermission == android.Manifest.permission.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS
                && _batteryOptimizationPrompted.value) {
                permissionNavigator.next()
            }
        }
    }

    fun onPermissionDenied() {
        viewModelScope.launch {
            permissionNavigator.next()
        }

    }


    private val _permissionStrings: MutableStateFlow<PermissionString> = MutableStateFlow(
        PermissionString()
    )
    val permissionStrings: StateFlow<PermissionString> = _permissionStrings

    fun getStringsForPermission(config: PermissionConfig) {
        viewModelScope.launch {
            _permissionStrings.value = PermissionString(
                title = UiText.StringResource(config.titleKey),
                description = UiText.StringResource(
                    config.descriptionKey,
                    HechimTheme.brand.appName
                ),
                nextButton = UiText.StringResource(config.nextButtonKey),
                questionButton = UiText.StringResource(config.questionButtonString),
                sheetTitle = UiText.StringResource(config.sheetTitleKey),
                sheetDescription = UiText.StringResource(
                    config.sheetDescriptionKey,
                    HechimTheme.brand.appName
                ),
                sheetButton = UiText.StringResource(config.sheetButtonKey),
            )

        }
    }
}

