package com.semirsuljevic.dashboard.api.changePassword.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semirsuljevic.dashboard.R
import com.semirsuljevic.dashboard.api.applicationSettings.ui.RouteApplicationSettings
import com.semirsuljevic.dashboard.api.changePassword.ui.RouteChangePasswordSuccess
import com.semirsuljevic.foundation.api.authentication.HechimAuthentication
import com.semirsuljevic.foundation.api.common.HechimResource
import com.semirsuljevic.foundation.api.common.UiText
import com.semirsuljevic.ui.api.navigation.Navigator
import com.semirsuljevic.ui.api.screen.DefaultInformationScreenConfig
import com.semirsuljevic.ui.api.textfield.TextFieldConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChangePasswordViewModel @Inject constructor(
    private val hechimAuthentication: HechimAuthentication,
    private val changePasswordFormValidator: ChangePasswordFormValidator,
    private val navigator: Navigator
): ViewModel(){

    private val _resource = mutableStateOf<HechimResource<Boolean>>(HechimResource.Nothing())
    val resource get() = _resource.value

    fun resetResource() {
        onOldChanged("")
        onNewChanged("")
        onConfirmedChanged("")
        _resource.value = HechimResource.Nothing("")
    }



    private val _oldPassword = mutableStateOf(TextFieldConfig())
    val oldPassword get() = _oldPassword.value

    private val _newPassword = mutableStateOf(TextFieldConfig())
    val newPassword get() = _newPassword.value

    private val _confirmedPassword = mutableStateOf(TextFieldConfig())
    val confirmedPassword get() = _confirmedPassword.value

    init {
        collectValidator()
    }

    fun onOldChanged(value: String) = changePasswordFormValidator.setOldPassword(value)
    fun onNewChanged(value: String) = changePasswordFormValidator.setNewPassword(value)
    fun onConfirmedChanged(value: String) = changePasswordFormValidator.setConfirmPassword(value)

    fun invertOldPasswordVisibility() = changePasswordFormValidator.invertOldPasswordVisibility()
    fun invertNewPasswordVisibility() = changePasswordFormValidator.invertNewPasswordVisibility()
    fun invertConfirmPasswordVisibility() = changePasswordFormValidator.invertConfirmPasswordVisibility()

    private fun collectValidator() {
        viewModelScope.launch {
            launch { changePasswordFormValidator.oldPasswordConfig.collectLatest { _oldPassword.value = it } }
            launch { changePasswordFormValidator.newPasswordConfig.collectLatest { _newPassword.value = it } }
            launch { changePasswordFormValidator.confirmPasswordConfig.collectLatest { _confirmedPassword.value = it } }
        }
    }


    fun changePassword() {
        viewModelScope.launch {
            if(!changePasswordFormValidator.validateForm()) {
                return@launch
            }
            _resource.value = HechimResource.Loading()
            _resource.value = hechimAuthentication.changePassword(
                oldPassword = _oldPassword.value.text,
                newPassword = _newPassword.value.text
            )
            if(_resource.value is HechimResource.Success) {
                navigator.navigate(RouteChangePasswordSuccess())
            }
        }
    }

    fun trailingIcon(value: TextFieldConfig) = if(value.visualTransformation == PasswordVisualTransformation())
        R.drawable.ic_hide else R.drawable.ic_show

}
