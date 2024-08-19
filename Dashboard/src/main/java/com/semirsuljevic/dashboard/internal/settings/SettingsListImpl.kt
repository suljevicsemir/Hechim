package com.semirsuljevic.dashboard.internal.settings

import androidx.compose.runtime.MutableState
import com.semirsuljevic.dashboard.R
import com.semirsuljevic.dashboard.api.settings.viewmodel.SettingsList
import com.semirsuljevic.dashboard.api.ui.RouteDashboard
import com.semirsuljevic.foundation.api.common.UiText
import com.semirsuljevic.ui.api.common.HechimListItemConfig
import com.semirsuljevic.ui.api.navigation.Navigator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

class SettingsListImpl @Inject constructor(
    private val navigator: Navigator
): SettingsList{


    override val settingsList: Flow<List<HechimListItemConfig>> get() = _settingsList.asSharedFlow()

    private val _settingsList = MutableStateFlow<List<HechimListItemConfig>>( listOf(
        HechimListItemConfig(
            title = UiText.StringResource(R.string.settings_profile_title),
            description = UiText.StringResource(R.string.settings_profile_desc),
            icon = R.drawable.ic_user_profile,
            onClick = { navigator.navigate(RouteDashboard()) }
        ),
        HechimListItemConfig(
            title = UiText.StringResource(R.string.settings_app_settings_title),
            description = UiText.StringResource(R.string.settings_app_settings_desc),
            icon = R.drawable.ic_app_settings,
            onClick = { navigator.navigate(RouteDashboard()) }
        ),
        HechimListItemConfig(
            title = UiText.StringResource(R.string.settings_legal_title),
            description = UiText.StringResource(R.string.settings_legal_desc),
            icon = R.drawable.ic_legal_info,
            onClick = { navigator.navigate(RouteDashboard()) }
        ),
        HechimListItemConfig(
            title = UiText.StringResource(R.string.settings_about_title, "FitnessPal"),
            description = UiText.StringResource(R.string.settings_about_desc),
            icon = R.drawable.ic_about_app,
            onClick = { navigator.navigate(RouteDashboard()) }
        ),
        HechimListItemConfig(
            title = UiText.StringResource(R.string.settings_log_out_title),
            description = UiText.StringResource(R.string.settings_log_out_desc),
            icon = R.drawable.ic_logout,
            onClick = { navigator.navigate(RouteDashboard()) }
        )

    )
    )
}
