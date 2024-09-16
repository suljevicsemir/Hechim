package com.semirsuljevic.dashboard.api.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.semirsuljevic.dashboard.api.navigation.DashboardHomeItem
import com.semirsuljevic.dashboard.api.navigation.DashboardMoreItem
import com.semirsuljevic.dashboard.api.navigation.DashboardProfileItem
import com.semirsuljevic.dashboard.api.navigation.DashboardWorkoutsItem
import com.semirsuljevic.dashboard.api.navigation.dashboardItems
import com.semirsuljevic.dashboard.api.settings.ui.SettingsPage
import com.semirsuljevic.dashboard.api.viewmodel.DashboardViewModel
import com.semirsuljevic.ui.api.navbar.HechimNavigationBar
import com.semirsuljevic.ui.api.navigation.HechimRoute
import com.semirsuljevic.ui.api.screen.HechimScreen
import com.semirsuljevic.ui.api.screen.HechimScreenConfig
import com.semirsuljevic.ui.api.theme.HechimTheme

class RouteDashboard: HechimRoute("dashboard")

@Composable
fun DashboardScreen(
    navController: NavHostController = rememberNavController(),
    viewModel: DashboardViewModel = hiltViewModel()
) {

    LaunchedEffect(Unit) { viewModel.setNavController(navController) }

    HechimScreen (
        config = HechimScreenConfig(canNavigateBack = false),
        bottomBar = {
            HechimNavigationBar(
                navBarIndex = viewModel.navBarIndex,
                onClick = viewModel::navigateToDashboardItem,
                items = dashboardItems
            )
        }
    ){
        NavHost(
            navController = navController,
            startDestination = DashboardHomeItem.route,
            modifier = Modifier
                .padding(it)
                .padding(HechimTheme.sizes.small)
        ) {
            composable(DashboardHomeItem.route) {
                Column {

                }
            }
            composable(DashboardProfileItem.route) {
                Column {

                }
            }
            composable(DashboardMoreItem.route) {
                SettingsPage()
            }
            composable(DashboardWorkoutsItem.route) {
                Column {

                }
            }
        }
    }
}
