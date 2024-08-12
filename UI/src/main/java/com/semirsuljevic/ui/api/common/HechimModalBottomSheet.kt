@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.semirsuljevic.ui.api.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.semirsuljevic.ui.api.theme.HechimTheme

@Composable
fun HechimModalBottomSheet(
    onDismissRequest: () -> Unit,
    visible: Boolean,
    modifier: Modifier = Modifier,
    state: SheetState = SheetState(
        initialValue = SheetValue.Hidden,
        skipPartiallyExpanded = true,
        skipHiddenState = false
    ),
    containerColor: Color = HechimTheme.colors.backgroundDefault,
    shape: RoundedCornerShape = RoundedCornerShape(
        topEnd = HechimTheme.sizes.large,
        topStart = HechimTheme.sizes.large
    ),
    windowInsets: WindowInsets = BottomSheetDefaults.windowInsets,
    useBoxHandle: Boolean = true,
    content: @Composable (() -> Unit?)
) {
    if(visible)
        ModalBottomSheet(
            onDismissRequest = {
                onDismissRequest()
            },
            content = {
                Column (
                    modifier = modifier.padding(HechimTheme.sizes.xLarge)
                ){
                    content()
                }
            },
            windowInsets = windowInsets,
            sheetState = state,
            containerColor = containerColor,
            shape = shape,
            dragHandle = {
                if(useBoxHandle) {
                    Box(modifier = Modifier
                        .padding(vertical = HechimTheme.sizes.small, horizontal = HechimTheme.sizes.large)
                        .height(HechimTheme.sizes.xxSmall)
                        .width(HechimTheme.sizes.large)
                        .background(
                            color = HechimTheme.colors.textDefault,
                            shape = HechimTheme.shapes.smallRoundedCorner
                        )

                    )
                }
            }
        )
}
