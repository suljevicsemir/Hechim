package com.semirsuljevic.onboarding.internal.welcome.name

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.hechimdemo.onboarding.R
import com.semirsuljevic.foundation.api.common.UiText
import com.semirsuljevic.onboarding.api.welcome.ui.name.ui.NameFormValidator
import com.semirsuljevic.ui.api.textfield.TextFieldConfig
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

internal class NameFormValidatorImpl @Inject constructor(): NameFormValidator{
    override fun setFirstName(value: String) {
        _firstNameConfig.update { _firstNameConfig.value.copy(text = value) }
        _formValid.update {
            validateForm()
        }
    }

    override fun setLastName(value: String) {
        _lastNameConfig.update { _lastNameConfig.value.copy(text = value) }
    }

    private fun validateForm() = _firstNameConfig.value.text.length >= 2

    override val firstNameConfig: Flow<TextFieldConfig> get() = _firstNameConfig.asSharedFlow()
    override val lastNameConfig: Flow<TextFieldConfig> get() = _lastNameConfig.asSharedFlow()
    override val formValid: MutableStateFlow<Boolean> get() = _formValid

    private val _firstNameConfig = MutableStateFlow(TextFieldConfig(
        hint = UiText.StringResource(R.string.name_first_name_hint),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Next)
    ))
    private val _lastNameConfig = MutableStateFlow(TextFieldConfig(
        hint = UiText.StringResource(R.string.name_last_name_hint),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Done)
    ))
    private val _formValid = MutableStateFlow(false)
}
