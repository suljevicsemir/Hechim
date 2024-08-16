package com.semirsuljevic.ui.api.textfield

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.VisualTransformation
import com.semirsuljevic.foundation.api.common.UiText

data class TextFieldConfig(
    val text: String = "",
    val hint: UiText = UiText.StringValue(""),
    val visualTransformation: VisualTransformation = VisualTransformation.None,
    val keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    val errorText: UiText = UiText.StringValue("")
)
