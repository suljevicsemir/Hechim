package com.semirsuljevic.onboarding.api.welcome.ui.login

import com.semirsuljevic.ui.api.textfield.TextFieldConfig
import kotlinx.coroutines.flow.Flow
import org.w3c.dom.Text

interface LoginFormValidator {
    fun setEmail(value: String)
    fun setPassword(value: String)

    fun validateForm(): Boolean

    fun invertPasswordVisibility()

    val emailConfig: Flow<TextFieldConfig>
    val passwordConfig: Flow<TextFieldConfig>
}
