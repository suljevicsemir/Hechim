package com.semirsuljevic.ui.api.textfield

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import com.semirsuljevic.ui.api.theme.HechimTheme

@Composable
fun HechimTextField(
    value: String,
    onValueChange: (String) -> Unit,
    borderColor: Color = HechimTheme.colors.textFieldInactive,
    hintColor: Color = HechimTheme.colors.textFieldInactive,
    hint: String,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    errorText: String = ""
) {

    Column (
        horizontalAlignment = Alignment.Start
    ){
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            textStyle = TextStyle(
                color = HechimTheme.colors.textDefault,
                fontSize = HechimTheme.fonts.hint.fontSize
            ),
            decorationBox = { innerTextField ->
                if (value.isEmpty()) {
                    Text(
                        hint,
                        color = hintColor,
                        style = HechimTheme.fonts.hint,
                    )
                }
                innerTextField()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = HechimTheme.sizes.medium)
                .drawBehind {
                    drawLine(
                        color = borderColor,
                        start = Offset(0f, size.height),
                        end = Offset(size.width, size.height),
                        strokeWidth = HechimTheme.sizes.textFieldBorderWidth.toPx()
                    )
                }
                .padding(bottom = HechimTheme.sizes.small),
            keyboardOptions = keyboardOptions,
        )
        Text(
            errorText,
            color = HechimTheme.colors.primary,
            style = HechimTheme.fonts.labelXXSmall
        )
    }
}
