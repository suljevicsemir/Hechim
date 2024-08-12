package com.semirsuljevic.ui.api.buttons

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.semirsuljevic.ui.api.theme.HechimTheme

@Composable
fun HechimPaddedButton(
    onClick : () -> Unit,
    text : String,
    modifier : Modifier = Modifier,
    contentPaddingValues : PaddingValues = PaddingValues(
        horizontal = HechimTheme.sizes.xxLarge,
        vertical = HechimTheme.sizes.large
    )
) {
    Button(
        modifier = modifier,
        onClick = { onClick.invoke() },
        content = {
            Text(
                text,
                style = HechimTheme.fonts.buttonText,
                color = HechimTheme.colors.surfaceBackground
            )
        },
        colors = ButtonDefaults.buttonColors(containerColor = HechimTheme.colors.primary),
        shape = HechimTheme.shapes.xLargeRoundedCorner,
        contentPadding = contentPaddingValues
    )
}
