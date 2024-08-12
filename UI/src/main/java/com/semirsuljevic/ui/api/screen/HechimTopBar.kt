package com.semirsuljevic.ui.api.screen

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import com.semirsuljevic.ui.R
import com.semirsuljevic.ui.api.buttons.HechimIconButton
import com.semirsuljevic.ui.api.theme.HechimTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HechimTopBar(
    config: HechimScreenConfig,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    colors: TopAppBarColors = TopAppBarDefaults.mediumTopAppBarColors(
        containerColor = HechimTheme.colors.backgroundDefault,
    ),
    actions: @Composable (RowScope.() -> Unit)? = null,
) {
    CenterAlignedTopAppBar(
        title = {
            if(config.title != null) {
                Text(
                    config.title,
                    color = HechimTheme.colors.textDefault,
                    style = HechimTheme.fonts.pageTitle
                )
            }
        },
        colors = colors,
        modifier = modifier.padding(horizontal = 18.dp),
        actions = {
              if(actions != null) {
                  actions()
              }
        },
        navigationIcon = {
            if (config.canNavigateBack) {
                HechimIconButton(
                    icon = R.drawable.ic_arrow_right,
                    onClick = { navigateUp() },
                    modifier = Modifier.rotate(degrees = 90f)
                )
            }
        }
    )
}
