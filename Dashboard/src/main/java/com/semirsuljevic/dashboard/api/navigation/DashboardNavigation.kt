package com.semirsuljevic.dashboard.api.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.semirsuljevic.dashboard.api.aboutUs.ui.AboutUsScreen
import com.semirsuljevic.dashboard.api.aboutUs.ui.RouteAboutUs
import com.semirsuljevic.dashboard.api.legal.ui.LegalScreen
import com.semirsuljevic.dashboard.api.legal.ui.PrivacyPolicyScreen
import com.semirsuljevic.dashboard.api.legal.ui.RouteLegal
import com.semirsuljevic.dashboard.api.legal.ui.RoutePrivacyPolicy
import com.semirsuljevic.dashboard.api.legal.ui.RouteTerms
import com.semirsuljevic.dashboard.api.legal.ui.TermsScreen
import com.semirsuljevic.dashboard.api.ui.DashboardScreen
import com.semirsuljevic.dashboard.api.ui.RouteDashboard

fun NavGraphBuilder.dashboardNavGraph() {
    composable(RouteDashboard().path) {
        DashboardScreen()
    }

    //LEGAL
    composable(RouteLegal().path) { LegalScreen() }
    composable(RouteTerms().path) { TermsScreen() }
    composable(RoutePrivacyPolicy().path) { PrivacyPolicyScreen() }

    //ABOUT US
    composable(RouteAboutUs().path) { AboutUsScreen() }

}
