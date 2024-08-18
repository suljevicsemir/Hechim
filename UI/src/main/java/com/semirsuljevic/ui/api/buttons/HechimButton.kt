package com.semirsuljevic.ui.api.buttons

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.semirsuljevic.ui.api.theme.HechimTheme

@Composable
fun HechimButton(
    onClick: () -> Unit,
    text: String,
    enabled: Boolean = true
) {
    Button(
        onClick = {
            onClick()
        },
        content = {
            Text(
                text = text,
                style = HechimTheme.fonts.buttonText,
                color = HechimTheme.colors.backgroundSecondary
            )
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = HechimTheme.colors.primary,
        ),
        enabled = enabled,
        shape = HechimTheme.shapes.xLargeRoundedCorner,
        modifier = Modifier
            .clip(HechimTheme.shapes.xLargeRoundedCorner)
            .fillMaxWidth(),
        contentPadding = PaddingValues(vertical = HechimTheme.sizes.medium)
    )
}
