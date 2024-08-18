package com.semirsuljevic.onboarding.api.permissions.ui

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.semirsuljevic.onboarding.api.permissions.config.PermissionConfig
import com.semirsuljevic.onboarding.api.permissions.config.PermissionString
import com.semirsuljevic.onboarding.api.permissions.viewmodel.PermissionViewModel
import com.semirsuljevic.onboarding.internal.permissions.PermissionDialog
import com.semirsuljevic.onboarding.internal.permissions.PermissionSheet
import com.semirsuljevic.onboarding.internal.permissions.PermissionType
import com.semirsuljevic.onboarding.internal.permissions.PermissionsScreenLifecycle
import com.semirsuljevic.ui.api.buttons.HechimButton
import com.semirsuljevic.ui.api.buttons.HechimTextButton
import com.semirsuljevic.ui.api.common.HechimHtmlText
import com.semirsuljevic.ui.api.screen.HechimScreen
import com.semirsuljevic.ui.api.screen.HechimScreenConfig
import com.semirsuljevic.ui.api.theme.HechimTheme

@Composable
fun PermissionScreen(
    permission: PermissionConfig,
    viewModel: PermissionViewModel
) {

    LaunchedEffect(Unit) {
        viewModel.getStringsForPermission(permission)
    }

    val strings: PermissionString by viewModel.permissionStrings.collectAsStateWithLifecycle()

    //register launcher for permissions screen for each permission
    //compared to rememberPermissionState() will be invoked only when request is executed
    //then we feed info back to view model, which then decides to navigate further or settings
    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            viewModel.onPermissionGranted(permission)
        } else {
            viewModel.onPermissionDenied()
        }
    }

    //launcher only used for Android 10 - location permission
    //as location on Android must be requested using multiple permission request
    val android10Location = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { map ->
        //ignore results if not Android 10 Location
        if(permission.permissionType != PermissionType.BackgroundLocation) {
            return@rememberLauncherForActivityResult
        }
        val granted = map.getOrDefault(Manifest.permission.ACCESS_BACKGROUND_LOCATION, false)
        if (granted) {
            viewModel.onPermissionGranted(permission)
        } else {
            viewModel.onPermissionDenied()
        }
    }

    PermissionsScreenLifecycle(permission = permission, viewModel = viewModel)


    PermissionSheet(
        title = strings.sheetTitle,
        description = strings.sheetDescription,
        viewModel = viewModel,
        button = strings.sheetButton
    )

    PermissionDialog(viewModel = viewModel, launcher)

    HechimScreen (
        config = HechimScreenConfig(
            canNavigateBack = false
        )
    ){
        Column (
            modifier = Modifier
                .padding(it)
                .padding(HechimTheme.sizes.scaffoldHorizontal),
        ){
            Column (
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
                    .padding(HechimTheme.sizes.large),
                verticalArrangement = Arrangement.Center
            ){
                Image(
                    painter = painterResource(id = permission.image),
                    contentDescription = strings.title.asString(),
                    colorFilter = ColorFilter.tint(HechimTheme.colors.primary)
                )
                Spacer(modifier = Modifier.height(HechimTheme.sizes.medium))
                Text(
                    strings.title.asString(),
                    style = HechimTheme.fonts.regularTitle,
                    color = HechimTheme.colors.textDefault
                )
                Spacer(modifier = Modifier.height(HechimTheme.sizes.large))
                HechimHtmlText(
                    html = strings.description.asString(),
                    style = TextStyle(
                        color = HechimTheme.colors.textDefault,
                        fontSize = HechimTheme.fonts.pageTitle.fontSize
                    )
                )
            }
            HechimButton(
                onClick = {
                    viewModel.onNextButtonTapped(
                        launcher,
                        permission.manifestPermission,
                        android10Location,
                        permission.multiplePermissions
                    )
                },
                enabled = true,
                text = strings.nextButton.asString()
            )
            Spacer(modifier = Modifier.height(HechimTheme.sizes.xxSmall))
            HechimTextButton(
                onClick = {
                    viewModel.setSheetVisibility()
                },
                text = strings.questionButton.asString(),
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(HechimTheme.sizes.large))
        }
    }


}

