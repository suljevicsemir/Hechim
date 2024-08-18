package com.semirsuljevic.ui.api.navbar

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.semirsuljevic.ui.api.theme.HechimTheme

@Composable
fun HechimNavigationBar(
    navBarIndex: Int,
    onClick: (Int, HechimNavigationBarItem) -> Unit,
    items: List<HechimNavigationBarItem>
) {
    NavigationBar(
        containerColor = HechimTheme.colors.surfaceBackground,
    ) {
        items.forEachIndexed { index, dashboardItem ->
            NavigationBarItem(
                selected = navBarIndex == index,
                onClick = {
                    onClick(index, dashboardItem)
                },
                icon = {
                    Icon(
                        painter = painterResource(id = dashboardItem.icon),
                        contentDescription = null
                    )
                },
                label = {
                    Text(
                        dashboardItem.label.asString(),
                        style = HechimTheme.fonts.labelXSmall,
                        softWrap = false
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = HechimTheme.colors.primary,
                    selectedTextColor = HechimTheme.colors.primary,
                    unselectedIconColor = HechimTheme.colors.textDefault,
                    unselectedTextColor = HechimTheme.colors.textDefault
                )
            )
        }
    }
}
