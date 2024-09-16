package com.semirsuljevic.dashboard.api.changePassword.ui

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.semirsuljevic.dashboard.R
import com.semirsuljevic.dashboard.api.applicationSettings.ui.RouteApplicationSettings
import com.semirsuljevic.foundation.api.common.UiText
import com.semirsuljevic.ui.api.navigation.HechimRoute
import com.semirsuljevic.ui.api.navigation.NavigationViewModel
import com.semirsuljevic.ui.api.screen.DefaultInformationScreen
import com.semirsuljevic.ui.api.screen.DefaultInformationScreenConfig

class RouteChangePasswordSuccess: HechimRoute("change_password_success")

@Composable
fun ChangePasswordSuccess( viewModel: NavigationViewModel = hiltViewModel()) {
    DefaultInformationScreen(
        config = DefaultInformationScreenConfig(
            image = R.drawable.img_password_changed,
            title = UiText.StringResource(R.string.change_pass_success_title),
            description = UiText.StringResource(R.string.change_pass_success_description),
            button = UiText.StringResource(R.string.change_pass_success_button),
            onButtonClick = {
                viewModel.popUntil(RouteApplicationSettings(), true)
            }
        )
    )
}
