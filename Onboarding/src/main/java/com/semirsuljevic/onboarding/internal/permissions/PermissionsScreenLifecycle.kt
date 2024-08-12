package com.semirsuljevic.onboarding.internal.permissions

import androidx.compose.runtime.Composable
import androidx.lifecycle.Lifecycle
import com.semirsuljevic.onboarding.api.permissions.config.PermissionConfig
import com.semirsuljevic.onboarding.api.permissions.viewmodel.PermissionViewModel
import com.semirsuljevic.ui.api.common.ComposableLifecycle

@Composable
fun PermissionsScreenLifecycle(
    permission: PermissionConfig,
    viewModel: PermissionViewModel
) {
    ComposableLifecycle(
        onEvent = { _, event ->
            //ON_RESUME is called every time app is usable
            //back from background or system popup dismissed (permission)
            if(event == Lifecycle.Event.ON_RESUME) {
                viewModel.onResume(permission)
            }
            //ON_PAUSE is only triggered when system popup (permission) is displayed not when app is backgrounded
            //which means this case covers when permission is displayed, we only need this case for BatteryOptimization
            //control variable set to true
            if(event == Lifecycle.Event.ON_PAUSE && permission.permissionType == PermissionType.BatteryOptimization) {
                viewModel.resetBatteryOptimizationPrompted(true)
            }
            //ON_STOP is triggered when app is backgrounded by user not when system popup is displayed
            //if battery optimization popup is displayed it will be closed when app is backgrounded automatically
            // by system, so that's why we set the control variable to false
            if(event == Lifecycle.Event.ON_STOP) {
                viewModel.resetBatteryOptimizationPrompted(false)
            }
        }
    )
}
