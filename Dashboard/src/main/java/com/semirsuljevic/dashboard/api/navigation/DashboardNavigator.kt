package com.semirsuljevic.dashboard.api.navigation

import androidx.navigation.NavHostController
import com.semirsuljevic.ui.api.navbar.HechimNavigationBarItem
import kotlinx.coroutines.flow.Flow

interface DashboardNavigator {
    fun setIndex(value: Int)
    fun setNavController(navController: NavHostController)

    fun navigate(dashboardItem: HechimNavigationBarItem)

    val indexFlow: Flow<Int>
}
