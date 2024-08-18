package com.semirsuljevic.onboarding.api.permissions.navigation

import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.semirsuljevic.onboarding.api.permissions.config.PermissionConstants
import com.semirsuljevic.onboarding.api.permissions.ui.PermissionScreen
import com.semirsuljevic.onboarding.api.permissions.viewmodel.PermissionViewModel
import com.semirsuljevic.onboarding.api.welcome.ui.email.RouteEmail
import com.semirsuljevic.onboarding.internal.permissions.PermissionRoute
import com.semirsuljevic.onboarding.internal.permissions.PermissionType

fun NavGraphBuilder.permissionsNavGraph(viewModelStoreOwner: ViewModelStoreOwner) {
    composable(PermissionRoute(PermissionType.ActivityRecognition).path) { entry ->
        PermissionScreen(
            permission = PermissionConstants.activityRecognition,
            viewModel = viewModel<PermissionViewModel>(viewModelStoreOwner)
        )
    }
    composable(PermissionRoute(PermissionType.Notification).path) { entry ->
        PermissionScreen(
            permission = PermissionConstants.notification,
            viewModel = viewModel<PermissionViewModel>(viewModelStoreOwner)
        )
    }
    composable(PermissionRoute(PermissionType.Bluetooth).path) { entry ->
        PermissionScreen(
            permission = PermissionConstants.bluetooth,
            viewModel = viewModel<PermissionViewModel>(viewModelStoreOwner)
        )
    }
    composable(PermissionRoute(PermissionType.LocationWhileInUse).path) { entry ->
        PermissionScreen(
            permission = PermissionConstants.locationAndroid10,
            viewModel = viewModel<PermissionViewModel>(viewModelStoreOwner)
        )

    }
    composable(PermissionRoute(PermissionType.BackgroundLocation).path) { entry ->
        PermissionScreen(
            permission = PermissionConstants.locationAndroid10,
            viewModel = viewModel<PermissionViewModel>(viewModelStoreOwner)
        )
    }

    composable(PermissionRoute(PermissionType.LocationWhileInUse).path) { entry ->
        PermissionScreen(
            permission = PermissionConstants.locationWhileInUse,
            viewModel = viewModel<PermissionViewModel>(viewModelStoreOwner)
        )
    }

    composable(PermissionRoute(PermissionType.Location).path) { entry ->
        PermissionScreen(
            permission = PermissionConstants.location9,
            viewModel = viewModel<PermissionViewModel>(viewModelStoreOwner)
        )
    }

    composable(PermissionRoute(PermissionType.BatteryOptimization).path) { entry ->
        PermissionScreen(
            permission = PermissionConstants.batteryOptimisation,
            viewModel = viewModel<PermissionViewModel>(viewModelStoreOwner)
        )
    }
}

