package com.semirsuljevic.onboarding.internal.welcome.register


import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.hechimdemo.onboarding.R
import com.semirsuljevic.foundation.api.authentication.CredentialsValidator
import com.semirsuljevic.foundation.api.common.UiText
import com.semirsuljevic.onboarding.api.welcome.ui.register.RegisterFormValidator
import com.semirsuljevic.ui.api.textfield.TextFieldConfig
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class RegisterFormValidatorImpl @Inject constructor(
    private val credentialsValidator: CredentialsValidator
): RegisterFormValidator{
    override fun setEmail(value: String) {
        _emailConfig.update { _emailConfig.value.copy(text = value.trim(), errorText = UiText.StringValue("")) }
    }

    override fun setPassword(value: String) {
        _passwordConfig.update { _passwordConfig.value.copy(text = value.trim(), errorText = UiText.StringValue("")) }
    }

    override fun invertPasswordVisibility() {
        _passwordConfig.update {
            _passwordConfig.value.copy(visualTransformation = invertVisualTransformation(_passwordConfig.value))
        }
    }

    override fun setConfirmPassword(value: String) {
        _confirmPasswordConfig.update { _confirmPasswordConfig.value.copy(text = value.trim(), errorText = UiText.StringValue("")) }
    }

    override fun invertConfirmPasswordVisibility() {
        _confirmPasswordConfig.update {
            _confirmPasswordConfig.value.copy(visualTransformation = invertVisualTransformation(_confirmPasswordConfig.value))
        }
    }

    override fun validateForm(): Boolean {
        if(!credentialsValidator.validateEmail(_emailConfig.value.text)) {
            _emailConfig.update { _emailConfig.value.copy(errorText = UiText.StringResource(R.string.register_email_invalid)) }
            return false
        }
        if(!credentialsValidator.validatePassword(_passwordConfig.value.text)) {
            _passwordConfig.update { _passwordConfig.value.copy(errorText = UiText.StringResource(R.string.register_password_invalid)) }
            return false
        }
        if(_passwordConfig.value.text != _confirmPasswordConfig.value.text) {
            _confirmPasswordConfig.value = _confirmPasswordConfig.value.copy(errorText = UiText.StringResource(R.string.register_password_match))
            return false
        }
        return true
    }

    private fun invertVisualTransformation(value: TextFieldConfig): VisualTransformation {
        if(value.visualTransformation == PasswordVisualTransformation()) {
            return VisualTransformation.None
        }
        return PasswordVisualTransformation()
    }

    override val emailConfig: Flow<TextFieldConfig> get() = _emailConfig.asSharedFlow()
    override val passwordConfig: Flow<TextFieldConfig> get() = _passwordConfig.asSharedFlow()
    override val confirmPasswordConfig: Flow<TextFieldConfig> get() = _confirmPasswordConfig.asSharedFlow()


    private val _emailConfig = MutableStateFlow(TextFieldConfig(
        hint = UiText.StringResource(R.string.register_email_hint),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email, imeAction = ImeAction.Next)
    ))
    private val _passwordConfig = MutableStateFlow(TextFieldConfig(
        hint = UiText.StringResource(R.string.register_password_hint),
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Next)
    ))
    private val _confirmPasswordConfig = MutableStateFlow(TextFieldConfig(
        hint = UiText.StringResource(R.string.register_confirm_password_hint),
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Done)
    ))
}
