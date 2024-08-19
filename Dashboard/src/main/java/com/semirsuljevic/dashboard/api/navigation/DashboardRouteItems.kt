package com.semirsuljevic.dashboard.api.navigation

import com.semirsuljevic.dashboard.R
import com.semirsuljevic.foundation.api.common.UiText
import com.semirsuljevic.ui.api.navbar.HechimNavigationBarItem

val dashboardItems = listOf(DashboardHomeItem, DashboardProfileItem, DashboardMoreItem, DashboardWorkoutsItem)
data object DashboardHomeItem: HechimNavigationBarItem(
    label = UiText.StringResource(R.string.dashboard_item_home),
    icon = R.drawable.dashboard_home,
    route = "Dashboard",
    index = 0
)
data object DashboardProfileItem: HechimNavigationBarItem(
    label = UiText.StringResource(R.string.dashboard_item_profile),
    icon = R.drawable.dashboard_profile,
    route = "Profile",
    index = 1
)
data object DashboardMoreItem: HechimNavigationBarItem(
    label = UiText.StringResource(R.string.dashboard_item_more),
    icon = R.drawable.dashboard_more,
    route = "More",
    index = 2
)
data object DashboardWorkoutsItem: HechimNavigationBarItem(
    label = UiText.StringResource(R.string.dashboard_item_workouts),
    icon = R.drawable.dashboard_trips,
    route = "Workouts",
    index = 3
)

