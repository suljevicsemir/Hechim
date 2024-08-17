package com.semirsuljevic.onboarding.internal.welcome.login

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.hechimdemo.onboarding.R
import com.semirsuljevic.foundation.api.authentication.CredentialsValidator
import com.semirsuljevic.foundation.api.common.UiText
import com.semirsuljevic.onboarding.api.welcome.ui.login.LoginFormValidator
import com.semirsuljevic.ui.api.textfield.TextFieldConfig
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

internal class LoginFormValidatorImpl @Inject constructor(
    private val credentialsValidator: CredentialsValidator
): LoginFormValidator{
    override fun setEmail(value: String) {
        _emailConfig.update { _emailConfig.value.copy(text = value, errorText = UiText.StringValue("")) }
    }
    override fun setPassword(value: String) {
        _passwordConfig.update { _passwordConfig.value.copy(text = value, errorText = UiText.StringValue("")) }
    }

    override fun validateForm(): Boolean {
        if(!credentialsValidator.validateEmail(_emailConfig.value.text)) {
            _emailConfig.update {
                _emailConfig.value.copy(errorText = UiText.StringResource(R.string.login_invalid_email))
            }
            return false
        }
        return true
    }

    override fun invertPasswordVisibility() {
        _passwordConfig.update {
            _passwordConfig.value.copy(visualTransformation = invertVisualTransformation(_passwordConfig.value))
        }
    }

    override val emailConfig: Flow<TextFieldConfig> get() = _emailConfig.asSharedFlow()
    override val passwordConfig: Flow<TextFieldConfig> get() = _passwordConfig.asSharedFlow()

    private val _emailConfig = MutableStateFlow(TextFieldConfig(
        hint = UiText.StringResource(R.string.login_username_input_hint),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email, imeAction = ImeAction.Next)
    ))
    private val _passwordConfig = MutableStateFlow(TextFieldConfig(
        hint = UiText.StringResource(R.string.login_password_input_hint),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Done),
        visualTransformation = PasswordVisualTransformation()
    ))

    private fun invertVisualTransformation(value: TextFieldConfig): VisualTransformation {
        if(value.visualTransformation == PasswordVisualTransformation()) {
            return VisualTransformation.None
        }
        return PasswordVisualTransformation()
    }
}
