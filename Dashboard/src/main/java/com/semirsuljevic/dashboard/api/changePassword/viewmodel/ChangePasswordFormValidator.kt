package com.semirsuljevic.dashboard.api.changePassword.viewmodel

import com.semirsuljevic.ui.api.textfield.TextFieldConfig
import kotlinx.coroutines.flow.Flow

interface ChangePasswordFormValidator {

    fun setOldPassword(value: String)
    fun invertOldPasswordVisibility()
    fun setNewPassword(value: String)
    fun invertNewPasswordVisibility()
    fun setConfirmPassword(value: String)
    fun invertConfirmPasswordVisibility()

    fun validateForm(): Boolean

    val oldPasswordConfig: Flow<TextFieldConfig>
    val newPasswordConfig: Flow<TextFieldConfig>
    val confirmPasswordConfig: Flow<TextFieldConfig>
}
