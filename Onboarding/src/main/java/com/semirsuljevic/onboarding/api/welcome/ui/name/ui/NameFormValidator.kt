package com.semirsuljevic.onboarding.api.welcome.ui.name.ui

import com.semirsuljevic.ui.api.textfield.TextFieldConfig
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

interface NameFormValidator {

    fun setFirstName(value: String)
    fun setLastName(value: String)

    val firstNameConfig: Flow<TextFieldConfig>
    val lastNameConfig: Flow<TextFieldConfig>
    val formValid: MutableStateFlow<Boolean>

}
