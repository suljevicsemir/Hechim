package com.semirsuljevic.dashboard.internal.applicationSettings

import com.semirsuljevic.dashboard.R
import com.semirsuljevic.dashboard.api.applicationSettings.viewmodel.ApplicationSettingsList
import com.semirsuljevic.dashboard.api.biometrics.ui.RouteBiometrics
import com.semirsuljevic.dashboard.api.changePassword.ui.RouteChangePassword
import com.semirsuljevic.foundation.api.common.UiText
import com.semirsuljevic.ui.api.common.HechimListItemConfig
import com.semirsuljevic.ui.api.navigation.Navigator
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

internal class ApplicationSettingsListImpl @Inject constructor(
    private val navigator: Navigator
): ApplicationSettingsList {
    override val list: Flow<List<HechimListItemConfig>> get() = _list.asSharedFlow()

    private val _list = MutableStateFlow<List<HechimListItemConfig>>(
        value = listOf(
            HechimListItemConfig(
                title = UiText.StringResource(R.string.app_settings_change_pass_title),
                description = UiText.StringResource(R.string.app_settings_change_pass_desc),
                onClick = { navigator.navigate(RouteChangePassword()) },
                icon = R.drawable.ic_change_password
            ),
            HechimListItemConfig(
                title = UiText.StringResource(R.string.app_settings_biometrics_title),
                description = UiText.StringResource(R.string.app_settings_biometrics_desc),
                onClick = { navigator.navigate(RouteBiometrics()) },
                icon = R.drawable.ic_biometrics
            ),
            HechimListItemConfig(
                title = UiText.StringResource(R.string.app_settings_language_title),
                description = UiText.StringResource(R.string.app_settings_language_desc),
                onClick = { },
                icon = R.drawable.ic_language_selection
            )
        )
    )
}
