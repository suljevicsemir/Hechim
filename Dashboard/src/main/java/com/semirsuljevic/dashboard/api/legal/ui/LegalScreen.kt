package com.semirsuljevic.dashboard.api.legal.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.semirsuljevic.dashboard.R
import com.semirsuljevic.dashboard.api.legal.viewmodel.LegalViewModel
import com.semirsuljevic.foundation.api.common.UiText
import com.semirsuljevic.ui.api.common.HechimListItem
import com.semirsuljevic.ui.api.navigation.HechimRoute
import com.semirsuljevic.ui.api.screen.HechimScreen
import com.semirsuljevic.ui.api.screen.HechimScreenConfig
import com.semirsuljevic.ui.api.theme.HechimTheme

class RouteLegal: HechimRoute("legal")

@Composable
fun LegalScreen(
    viewModel: LegalViewModel = hiltViewModel()
) {
    HechimScreen (
        config = HechimScreenConfig(title = UiText.StringResource(R.string.legal_page_title).asString())
    ){
        Column (
            modifier = Modifier
                .padding(it)
                .padding(horizontal = HechimTheme.sizes.scaffoldHorizontal)
        ){
            viewModel.listItems.forEach { config ->
                HechimListItem(config = config)
            }
        }

    }
}
