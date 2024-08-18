package com.semirsuljevic.dashboard.api.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.semirsuljevic.dashboard.api.ui.DashboardScreen
import com.semirsuljevic.dashboard.api.ui.RouteDashboard

fun NavGraphBuilder.dashboardNavGraph() {
    composable(RouteDashboard().path) {
        DashboardScreen()
    }
}
