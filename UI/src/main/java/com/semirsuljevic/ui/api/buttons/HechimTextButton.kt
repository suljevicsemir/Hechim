package com.semirsuljevic.ui.api.buttons

import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.semirsuljevic.ui.api.theme.HechimTheme

@Composable
fun HechimTextButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    text: String,
    textColor: Color = HechimTheme.colors.textDefault,
) {
    TextButton(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier,
        content = {
            Text(
                text,
                color = textColor,
                fontSize = 16.sp,
                fontWeight = FontWeight.W600
            )
        }
    )
}
