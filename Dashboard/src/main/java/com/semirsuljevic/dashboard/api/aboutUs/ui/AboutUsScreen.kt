package com.semirsuljevic.dashboard.api.aboutUs.ui

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.semirsuljevic.dashboard.R
import com.semirsuljevic.dashboard.api.aboutUs.viewmodel.AboutUsViewModel
import com.semirsuljevic.foundation.api.common.UiText
import com.semirsuljevic.ui.api.common.HechimHtmlText
import com.semirsuljevic.ui.api.navigation.HechimRoute
import com.semirsuljevic.ui.api.screen.HechimScreen
import com.semirsuljevic.ui.api.screen.HechimScreenConfig
import com.semirsuljevic.ui.api.theme.HechimTheme

class RouteAboutUs: HechimRoute("about_us")

@Composable
fun AboutUsScreen(
    viewModel: AboutUsViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit){
        viewModel.fetchAboutUse()
    }

    val scrollState = rememberScrollState()
    val context = LocalContext.current

    HechimScreen (
        config = HechimScreenConfig(
            title = UiText.StringResource(R.string.about_page_title).asString()
        ),
        resource = viewModel.aboutUs
    ){
        Column (
            modifier = Modifier
                .padding(it)
                .padding(HechimTheme.sizes.scaffoldHorizontal)
                .verticalScroll(scrollState)
        ){
            Spacer(modifier = Modifier.height(HechimTheme.sizes.xLarge))
            HechimHtmlText(
                html = viewModel.aboutUsContent,
                onHyperlinkClick = { string ->
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(string))
                    context.startActivity(intent)
                }
            )
        }
    }
}
