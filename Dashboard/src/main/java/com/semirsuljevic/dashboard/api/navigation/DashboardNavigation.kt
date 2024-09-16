package com.semirsuljevic.dashboard.api.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.semirsuljevic.dashboard.api.aboutUs.ui.AboutUsScreen
import com.semirsuljevic.dashboard.api.aboutUs.ui.RouteAboutUs
import com.semirsuljevic.dashboard.api.applicationSettings.ui.ApplicationSettingsScreen
import com.semirsuljevic.dashboard.api.applicationSettings.ui.RouteApplicationSettings
import com.semirsuljevic.dashboard.api.changePassword.ui.ChangePasswordScreen
import com.semirsuljevic.dashboard.api.changePassword.ui.ChangePasswordSuccess
import com.semirsuljevic.dashboard.api.changePassword.ui.RouteChangePassword
import com.semirsuljevic.dashboard.api.changePassword.ui.RouteChangePasswordSuccess
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

    //APPLICATION SETTINGS (Leads to Change Password, Biometrics and Language Selection)
    composable(RouteApplicationSettings().path) { ApplicationSettingsScreen() }
    composable(RouteChangePassword().path) { ChangePasswordScreen() }
    composable(RouteChangePasswordSuccess().path) { ChangePasswordSuccess() }

    //ABOUT US
    composable(RouteAboutUs().path) { AboutUsScreen() }

}
