package com.semirsuljevic.ui.api.buttons

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.semirsuljevic.ui.api.theme.HechimTheme

@Composable
fun HechimIconButton(
    icon: Int,
    onClick: () -> Unit,
    invertColor: Boolean = false,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(45.dp)
            .border(
                1.5.dp,
                color = HechimTheme.colors.borderPrimary,
                shape = HechimTheme.shapes.largeRoundedCorner
            )
            .clip(HechimTheme.shapes.largeRoundedCorner)
            .background(HechimTheme.colors.surfaceBackground)
            .clickable {
                onClick.invoke()
            }){
        Image(
            painter = painterResource(id = icon),
            contentDescription = null,
            modifier = modifier.align(alignment = Alignment.Center)
        )
    }
}

@Preview
@Composable
fun HechimIconButtonPreview() {
    HechimIconButton(
        icon = com.google.android.material.R.drawable.material_ic_keyboard_arrow_left_black_24dp,
        onClick = { /*TODO*/ }
    )
}
