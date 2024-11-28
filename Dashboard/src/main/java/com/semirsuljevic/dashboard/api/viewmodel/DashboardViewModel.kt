package com.semirsuljevic.dashboard.api.viewmodel

import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.semirsuljevic.dashboard.api.navigation.DashboardNavigator
import com.semirsuljevic.foundation.api.sdk.room.dao.WorkoutDao
import com.semirsuljevic.ui.api.navbar.HechimNavigationBarItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val dashboardNavigator: DashboardNavigator,
    private val workoutDao: WorkoutDao
): ViewModel(){

    private val _navBarIndex = mutableIntStateOf(0)
    val navBarIndex get() = _navBarIndex.intValue

    fun setNavBarIndex(value: Int) {
        dashboardNavigator.setIndex(value)
    }

    init {
        collectDashboardNavigator()
    }

    private fun collectDashboardNavigator() {
        viewModelScope.launch {
            launch {
                dashboardNavigator.indexFlow.collectLatest {
                    _navBarIndex.intValue = it
                }
            }
        }
    }

    fun setNavController(navController: NavHostController) {
        dashboardNavigator.setNavController(navController)
    }

    fun navigateToDashboardItem(dashboardItem: HechimNavigationBarItem) {
        dashboardNavigator.navigate(dashboardItem)
    }
}
