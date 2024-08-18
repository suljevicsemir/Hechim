package com.semirsuljevic.onboarding.api.welcome.ui.register

import com.semirsuljevic.ui.api.textfield.TextFieldConfig
import kotlinx.coroutines.flow.Flow

interface RegisterFormValidator {
    fun setEmail(value: String)
    fun setPassword(value: String)
    fun invertPasswordVisibility()
    fun setConfirmPassword(value: String)
    fun invertConfirmPasswordVisibility()

    fun validateForm(): Boolean

    val emailConfig: Flow<TextFieldConfig>
    val passwordConfig: Flow<TextFieldConfig>
    val confirmPasswordConfig: Flow<TextFieldConfig>
}
