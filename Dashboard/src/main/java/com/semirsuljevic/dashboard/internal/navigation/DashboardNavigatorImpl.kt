package com.semirsuljevic.dashboard.internal.navigation

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.semirsuljevic.dashboard.api.navigation.DashboardNavigator
import com.semirsuljevic.foundation.api.common.Dispatchers
import com.semirsuljevic.ui.api.navbar.HechimNavigationBarItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class DashboardNavigatorImpl @Inject constructor(
    private val dispatchers: Dispatchers
): DashboardNavigator{
    override fun setIndex(value: Int) {
        _indexFlow.tryEmit(value)
    }

    override fun setNavController(navController: NavHostController) {
        _navControllerFlow.tryEmit(navController)
    }

    override fun navigate(dashboardItem: HechimNavigationBarItem) {
        CoroutineScope(dispatchers.main).launch {
            val navController = _navControllerFlow.first()
            setIndex(dashboardItem.index)
            navController.navigate(dashboardItem.route) {
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
        }
    }

    override val indexFlow: Flow<Int> get() = _indexFlow.asSharedFlow()

    private val _navControllerFlow = MutableSharedFlow<NavHostController>(replay = 1)
    private val _indexFlow = MutableSharedFlow<Int>(replay = 1)
}
