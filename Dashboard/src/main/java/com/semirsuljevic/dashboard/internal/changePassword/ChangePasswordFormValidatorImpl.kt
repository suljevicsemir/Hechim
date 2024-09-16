package com.semirsuljevic.dashboard.internal.changePassword

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.semirsuljevic.dashboard.R
import com.semirsuljevic.dashboard.api.changePassword.viewmodel.ChangePasswordFormValidator
import com.semirsuljevic.foundation.api.authentication.CredentialsValidator
import com.semirsuljevic.foundation.api.common.UiText
import com.semirsuljevic.ui.api.textfield.TextFieldConfig
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class ChangePasswordFormValidatorImpl @Inject constructor(
    private val credentialsValidator: CredentialsValidator
): ChangePasswordFormValidator {
    override fun setOldPassword(value: String) {
        _oldPasswordConfig.update {
            _oldPasswordConfig.value.copy(text = value.trim(), errorText = UiText.StringValue(""))
        }
    }

    override fun invertOldPasswordVisibility() {
        _oldPasswordConfig.update {
            _oldPasswordConfig.value.copy(visualTransformation = invertVisualTransformation(_oldPasswordConfig.value))
        }
    }

    private fun invertVisualTransformation(value: TextFieldConfig): VisualTransformation {
        if(value.visualTransformation == PasswordVisualTransformation()) {
            return VisualTransformation.None
        }
        return PasswordVisualTransformation()
    }

    override fun setNewPassword(value: String) {
        _newPasswordConfig.update {
            _newPasswordConfig.value.copy(text = value.trim(), errorText = UiText.StringValue(""))
        }
    }

    override fun invertNewPasswordVisibility() {
        _newPasswordConfig.update {
            _newPasswordConfig.value.copy(visualTransformation = invertVisualTransformation(_newPasswordConfig.value))
        }
    }

    override fun setConfirmPassword(value: String) {
        _confirmPasswordConfig.update {
            _confirmPasswordConfig.value.copy(text = value, errorText = UiText.StringValue(""))
        }
    }

    override fun invertConfirmPasswordVisibility() {
        _confirmPasswordConfig.update {
            _confirmPasswordConfig.value.copy(visualTransformation = invertVisualTransformation(_confirmPasswordConfig.value))
        }
    }

    override fun validateForm(): Boolean {
        if(!credentialsValidator.validatePassword(_oldPasswordConfig.value.text)) {
            _oldPasswordConfig.update { _oldPasswordConfig.value.copy(errorText = UiText.StringResource(R.string.new_password_invalid)) }
            return false
        }
        if(_oldPasswordConfig.value.text == _newPasswordConfig.value.text) {
            _newPasswordConfig.update { _newPasswordConfig.value.copy(errorText = UiText.StringResource(R.string.new_password_same_as_old)) }
            return false
        }
        if(!credentialsValidator.validatePassword(_newPasswordConfig.value.text)) {
            _newPasswordConfig.update { _newPasswordConfig.value.copy(errorText = UiText.StringResource(R.string.new_password_invalid)) }
            return false
        }
        if(_confirmPasswordConfig.value.text != _newPasswordConfig.value.text) {
            _confirmPasswordConfig.update { _confirmPasswordConfig.value.copy(errorText = UiText.StringResource(R.string.passwords_not_equal)) }
            return false
        }
        return true
    }

    override val oldPasswordConfig: Flow<TextFieldConfig> get() = _oldPasswordConfig.asSharedFlow()
    override val newPasswordConfig: Flow<TextFieldConfig> get() = _newPasswordConfig.asSharedFlow()
    override val confirmPasswordConfig: Flow<TextFieldConfig> get() = _confirmPasswordConfig.asSharedFlow()

    private val _oldPasswordConfig = MutableStateFlow(TextFieldConfig(
        hint = UiText.StringResource(R.string.change_pass_old_pass_hint),
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Next)
        )
    )

    private val _newPasswordConfig = MutableStateFlow(TextFieldConfig(
        hint = UiText.StringResource(R.string.change_pass_new_pass_hint),
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Next)
        )
    )

    private val _confirmPasswordConfig = MutableStateFlow(TextFieldConfig(
        hint = UiText.StringResource(R.string.change_pass_confirm_pass_hint),
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Done)
        )
    )
}
