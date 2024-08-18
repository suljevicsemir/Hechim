package com.semirsuljevic.ui.api.paging

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.semirsuljevic.ui.api.theme.HechimTheme

/**
    Pager indicator component, emphasises which item is currently selected.
    * @param selected - boolean flag if item is selected
    * @param selectedColor - optional override for the selected color
    * @param unselectedColor - optional override for the unselected color
 */
@Composable
fun PageIndexIndicator(
    selected: Boolean,
    selectedColor: Color = HechimTheme.colors.primary,
    unselectedColor : Color = HechimTheme.colors.surfaceBackground
) {
    if(selected) {
        SelectedIndicator(selectedColor)
        return
    }
    UnselectedIndicator(unselectedColor = unselectedColor)
}

@Composable
private fun SelectedIndicator(selectedColor: Color) {
    Box(
        Modifier
            .padding(horizontal = HechimTheme.sizes.xSmall)
            .clip(HechimTheme.shapes.mediumRoundedCorner)
            .width(HechimTheme.sizes.xLarge)
            .height(HechimTheme.sizes.medium)
            .background(color = selectedColor)
    )
}

@Composable
private fun UnselectedIndicator(unselectedColor: Color) {
    Box(
        Modifier
            .padding(horizontal = HechimTheme.sizes.xSmall)
            .clip(HechimTheme.shapes.mediumRoundedCorner)
            .size(HechimTheme.sizes.medium)
            .background(color = unselectedColor)
    )
}

@Preview
@Composable
internal fun PageIndexIndicatorPreview() {
    PageIndexIndicator(selected = true)
}
