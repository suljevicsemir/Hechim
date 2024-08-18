package com.semirsuljevic.ui.api.textfield

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import com.semirsuljevic.ui.api.theme.HechimTheme

@Composable
fun HechimTextField(
    onValueChange: (String) -> Unit,
    borderColor: Color = HechimTheme.colors.textFieldInactive,
    hintColor: Color = HechimTheme.colors.textFieldInactive,
    trailingIcon: Int? = null,
    onTrailingClick: (() -> Unit)? = null,
    config: TextFieldConfig = TextFieldConfig()
) {

    Column (
        horizontalAlignment = Alignment.Start
    ){
        Box (
            contentAlignment = Alignment.CenterEnd,
        ){
            if(trailingIcon != null) {
                IconButton(
                    onClick = { onTrailingClick?.invoke() },
                    content = {
                       Icon(
                           painter = painterResource(id = trailingIcon),
                           contentDescription = null,
                           tint = HechimTheme.colors.secondary,
                       )
                    },
                    modifier = Modifier.padding(bottom = HechimTheme.sizes.xSmall)
                )
            }
            BasicTextField(
                value = config.text,
                onValueChange = onValueChange,
                textStyle = TextStyle(
                    color = HechimTheme.colors.textDefault,
                    fontSize = HechimTheme.fonts.hint.fontSize
                ),
                decorationBox = { innerTextField ->
                    if (config.text.isEmpty()) {
                        Text(
                            config.hint.asString(),
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
                keyboardOptions = config.keyboardOptions,
                visualTransformation = config.visualTransformation
            )

        }
        Text(
            config.errorText.asString(),
            color = HechimTheme.colors.primary,
            style = HechimTheme.fonts.labelXXSmall
        )
    }
}
