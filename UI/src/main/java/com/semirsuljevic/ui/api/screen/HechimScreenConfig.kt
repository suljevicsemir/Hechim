package com.semirsuljevic.ui.api.screen

import androidx.compose.ui.graphics.Color
import com.semirsuljevic.ui.api.theme.HechimTheme

/**
    Config class for reusable HechimScreen. Reduces number of arguments
    for HechimScreen and simplifies invocation.
    * @param canNavigateBack - if user can pop the current screen. If false, no icon is shown.
     If true, icon is shown and top bar pops the current screen.
    * @param containerColor  - Scaffold's background color.
    * @param title - top bar's title. Titles are often used in a default design so we just pass
     string to incorporate it.
 */
data class HechimScreenConfig(
    val canNavigateBack: Boolean = true,
    val containerColor: Color = HechimTheme.colors.backgroundDefault,
    val title: String? = null,
    val errorReset: (() -> Unit)? = null
)
