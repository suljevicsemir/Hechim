package com.semirsuljevic.ui.api.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import com.semirsuljevic.foundation.api.common.HechimResource
import com.semirsuljevic.ui.R
import com.semirsuljevic.ui.api.buttons.HechimButton
import com.semirsuljevic.ui.api.navigation.NavigationViewModel
import com.semirsuljevic.ui.api.theme.HechimTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HechimScreen(
    config: HechimScreenConfig = HechimScreenConfig(),
    bottomBar: (@Composable () -> Unit)? = null,
    resource: HechimResource<Any>? = null,
    actions: @Composable (RowScope.() -> Unit)? = null,
    navigationViewModel: NavigationViewModel = hiltViewModel(),
    content: @Composable (PaddingValues) -> Unit,
) {
    Scaffold (
        containerColor = config.containerColor,
        bottomBar = {
            if(bottomBar != null) {
                bottomBar()
            }
        },
        topBar = {
            if(!config.canNavigateBack) {
                return@Scaffold
            }
            HechimTopBar(
                config = config,
                actions = actions,
                navigateUp = { navigationViewModel.pop() }
            )
        }
    ){
        content(it)
        AnimatedVisibility(visible = resource is HechimResource.Loading) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        HechimTheme.colors.backgroundDefault
                    ),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator(
                    color = HechimTheme.colors.primary

                )
            }
        }
        AnimatedVisibility(visible = resource is HechimResource.Error) {
            Column(
                modifier = Modifier
                    .padding(it)
                    .padding(horizontal = HechimTheme.sizes.scaffoldHorizontal)
                    .fillMaxSize()
                    .background(
                        HechimTheme.colors.backgroundDefault
                    ),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.weight(0.1f))
                Image(painter = painterResource(id = R.drawable.img_nothing_found), contentDescription = "Error")
                Spacer(modifier = Modifier.height(HechimTheme.sizes.xxLarge))
                if(resource is HechimResource.Error) {
                    Text(
                        resource.error.message,
                        style = HechimTheme.fonts.bodyLarge,
                        color = HechimTheme.colors.textDefault,
                        textAlign = TextAlign.Center
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                HechimButton(
                    onClick = {
                        config.errorReset?.invoke()
                    },
                    text = "Retry strategy"
                )
                Spacer(modifier = Modifier.height(HechimTheme.sizes.xLarge))
            }
        }
    }






}
