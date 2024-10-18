package com.semirsuljevic.dashboard.api.applicationSettings.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.semirsuljevic.dashboard.R
import com.semirsuljevic.dashboard.api.applicationSettings.viewmodel.ApplicationSettingsViewModel
import com.semirsuljevic.foundation.api.common.UiText
import com.semirsuljevic.ui.api.buttons.HechimButton
import com.semirsuljevic.ui.api.common.HechimListItem
import com.semirsuljevic.ui.api.navigation.HechimRoute
import com.semirsuljevic.ui.api.screen.HechimScreen
import com.semirsuljevic.ui.api.screen.HechimScreenConfig
import com.semirsuljevic.ui.api.theme.HechimTheme

class RouteApplicationSettings: HechimRoute("application_settings")

@Composable
fun ApplicationSettingsScreen(
    viewModel: ApplicationSettingsViewModel = hiltViewModel()
) {
    HechimScreen (
        config = HechimScreenConfig(
            title = UiText.StringResource(R.string.app_settings_page_title).asString()
        )
    ){
        Column (
            modifier = Modifier
                .padding(it)
                .padding(horizontal = HechimTheme.sizes.scaffoldHorizontal)
        ){
            HechimButton(onClick = { /*TODO*/ }, text = "start")
            viewModel.list.forEach { config ->
                HechimListItem(config = config)
            }
        }
    }
}
