package com.semirsuljevic.onboarding.internal.permissions

import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hechimdemo.onboarding.R
import com.semirsuljevic.foundation.api.common.UiText
import com.semirsuljevic.onboarding.api.permissions.viewmodel.PermissionViewModel
import com.semirsuljevic.ui.api.dialog.HechimDialog
import com.semirsuljevic.ui.api.dialog.HechimDialogConfig
import com.semirsuljevic.ui.api.theme.HechimTheme

@Composable
internal fun PermissionDialog(
    viewModel: PermissionViewModel,
    launcher: ManagedActivityResultLauncher<String, Boolean>
) {
    val visible: Boolean by viewModel.dialogVisible.collectAsStateWithLifecycle()

    HechimDialog(
        config = HechimDialogConfig(
            visible = visible,
            title = UiText.StringResource(R.string.permission_always_location_modal_title),
            description = UiText.StringResource(
                R.string.permission_always_location_modal_description,
                HechimTheme.brand.appName
            ),
            dismissButton = UiText.StringResource(R.string.permission_always_location_modal_cancel_button),
            confirmButton = UiText.StringResource(R.string.permission_always_location_modal_settings_button),
            onDismiss = {
                viewModel.hideDialog()
            },
            onConfirm = {
                viewModel.hideDialog()
                viewModel.requestPermission(
                    android.Manifest.permission.ACCESS_BACKGROUND_LOCATION,
                    launcher
                )
            }
          ),
        )
}
