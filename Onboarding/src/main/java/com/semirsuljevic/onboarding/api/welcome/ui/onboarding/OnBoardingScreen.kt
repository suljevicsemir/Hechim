@file:OptIn(ExperimentalFoundationApi::class)

package com.semirsuljevic.onboarding.api.welcome.ui.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.hechimdemo.onboarding.R
import com.semirsuljevic.onboarding.api.welcome.viewmodel.OnBoardingViewModel
import com.semirsuljevic.onboarding.internal.welcome.PageViewItem
import com.semirsuljevic.ui.api.buttons.HechimPaddedButton
import com.semirsuljevic.ui.api.navigation.HechimRoute
import com.semirsuljevic.ui.api.paging.PageIndexIndicator
import com.semirsuljevic.ui.api.screen.HechimScreen
import com.semirsuljevic.ui.api.screen.HechimScreenConfig
import com.semirsuljevic.ui.api.theme.HechimTheme
import kotlinx.coroutines.launch

class RouteOnBoarding: HechimRoute("onboarding")
class RouteOnBoardingPop: HechimRoute("onboarding_pop")

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(
    onBoardingViewModel: OnBoardingViewModel
) {
    val pagerState = rememberPagerState(pageCount = { onBoardingViewModel.items.size})
    val scrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect { onBoardingViewModel.setIndex(it) }
    }

    HechimScreen (
        config = HechimScreenConfig(
            containerColor = HechimTheme.colors.backgroundSecondary,
            title = stringResource(id = R.string.onboarding_top_bar),
            canNavigateBack = false
        )
    ){
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(it)
        ){
            Spacer(modifier = Modifier.height(HechimTheme.sizes.medium))
            Text(
                stringResource(id = R.string.onboarding_top_bar),
                color = HechimTheme.colors.textDefault,
                style = HechimTheme.fonts.pageTitle,
            )
            HorizontalPager(
                state = pagerState,
            ) { page ->
                PageViewItem(item = onBoardingViewModel.pagerItem(page))
            }
            Spacer(modifier = Modifier.weight(1f))
            Row (
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(HechimTheme.sizes.xLarge)
                    .fillMaxSize()
            ){
                Row {
                    PageIndexIndicator(selected = onBoardingViewModel.selectedIndex == 0)
                    PageIndexIndicator(selected = onBoardingViewModel.selectedIndex == 1)
                    PageIndexIndicator(selected = onBoardingViewModel.selectedIndex == 2)
                }
                HechimPaddedButton(
                    onClick = { coroutineScope.launch {
                        onBoardingViewModel.onButtonClick(pagerState)
                    }},
                    text = if (!onBoardingViewModel.onLastScreen) {
                        stringResource(id = R.string.onboarding_next_button)
                    } else stringResource(
                        id = R.string.onboarding_proceed
                    )
                )
            }
        }
    }

}
