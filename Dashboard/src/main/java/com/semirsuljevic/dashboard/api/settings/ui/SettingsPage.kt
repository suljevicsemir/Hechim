package com.semirsuljevic.dashboard.api.settings.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.semirsuljevic.dashboard.R
import com.semirsuljevic.dashboard.api.settings.viewmodel.SettingsViewModel
import com.semirsuljevic.foundation.api.common.UiText
import com.semirsuljevic.ui.api.common.HechimListItem
import com.semirsuljevic.ui.api.common.HechimListItemConfig
import com.semirsuljevic.ui.api.theme.HechimTheme

@Composable
fun SettingsPage(
    viewModel: SettingsViewModel = hiltViewModel()
) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            UiText.StringResource(R.string.settings_title).asString(),
            style = HechimTheme.fonts.pageTitleAlt,
            color = HechimTheme.colors.textDefault
        )
        Spacer(modifier = Modifier.height(HechimTheme.sizes.small))
        viewModel.list.forEach {
            HechimListItem(config = it)
        }
    }
}
