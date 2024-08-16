package com.semirsuljevic.onboarding.api.welcome.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hechimdemo.onboarding.R
import com.semirsuljevic.foundation.api.authentication.CredentialsValidator
import com.semirsuljevic.foundation.api.authentication.HechimAuthentication
import com.semirsuljevic.foundation.api.user.model.HechimUser
import com.semirsuljevic.foundation.api.common.HechimResource
import com.semirsuljevic.foundation.api.common.UiText
import com.semirsuljevic.onboarding.api.welcome.ui.name.ui.RouteName
import com.semirsuljevic.onboarding.api.welcome.ui.onboarding.RouteOnBoardingPop
import com.semirsuljevic.onboarding.api.welcome.ui.register.RegisterFormValidator
import com.semirsuljevic.ui.api.navigation.Navigator
import com.semirsuljevic.ui.api.textfield.TextFieldConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val navigator: Navigator,
    private val authentication: HechimAuthentication,
    private val credentialsValidator: CredentialsValidator,
    private val registerFormValidator: RegisterFormValidator
): ViewModel(){

    private val _resource = mutableStateOf<HechimResource<HechimUser>>(HechimResource.Nothing())
    val resource get() = _resource.value

    private val _emailConfig = mutableStateOf(TextFieldConfig())
    val emailConfig get() = _emailConfig.value

    private val _passwordConfig = mutableStateOf(TextFieldConfig())
    val passwordConfig get() = _passwordConfig.value

    private val _confirmPasswordConfig = mutableStateOf(TextFieldConfig(
        hint = UiText.StringResource(R.string.register_confirm_password_hint),
        visualTransformation = PasswordVisualTransformation()
    ))
    val confirmPasswordConfig get() = _confirmPasswordConfig.value

    fun setEmail(value: String) = registerFormValidator.setEmail(value)
    fun setPassword(value: String) = registerFormValidator.setPassword(value)
    fun setConfirmPassword(value: String) = registerFormValidator.setConfirmPassword(value)

    fun invertPasswordVisibility() = registerFormValidator.invertPasswordVisibility()
    fun invertConfirmPasswordVisibility() = registerFormValidator.invertConfirmPasswordVisibility()


    init {
        setupValidator()
    }

    private fun setupValidator() {
        viewModelScope.launch {
            launch { registerFormValidator.emailConfig.collectLatest { _emailConfig.value = it } }
            launch { registerFormValidator.passwordConfig.collectLatest { _passwordConfig.value = it } }
            launch { registerFormValidator.confirmPasswordConfig.collectLatest { _confirmPasswordConfig.value = it } }
        }
    }

    fun register() {
        if(!registerFormValidator.validateForm()) {
            return
        }
        viewModelScope.launch {
            _resource.value = HechimResource.Loading("")
            delay(1000)
            _resource.value = authentication.register(
                email = _emailConfig.value.text,
                password = _passwordConfig.value.text
            )
            if(_resource.value is HechimResource.Success) {
                //navigate to name screen
                navigator.navigate(RouteName())
            }
        }
    }

    val passwordIcon get() = if(_passwordConfig.value.visualTransformation == PasswordVisualTransformation())
        R.drawable.ic_hide else R.drawable.ic_show

    val confirmPasswordIcon get() = if(_confirmPasswordConfig.value.visualTransformation == PasswordVisualTransformation())
        R.drawable.ic_hide else R.drawable.ic_show


    fun resetState() {
        _resource.value = HechimResource.Nothing()
    }

    fun navigate() {
        navigator.navigate(RouteOnBoardingPop())
    }

}
