package com.semirsuljevic.onboarding.api.welcome.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hechimdemo.onboarding.R
import com.semirsuljevic.foundation.api.authentication.HechimAuthentication
import com.semirsuljevic.foundation.api.user.model.HechimUser
import com.semirsuljevic.foundation.api.common.HechimResource
import com.semirsuljevic.foundation.api.user.Profile
import com.semirsuljevic.foundation.api.user.ProfileProvider
import com.semirsuljevic.onboarding.api.permissions.PermissionsApi
import com.semirsuljevic.onboarding.api.permissions.navigation.PermissionsNavigator
import com.semirsuljevic.onboarding.api.welcome.ui.login.LoginFormValidator
import com.semirsuljevic.onboarding.api.welcome.ui.name.ui.RouteName
import com.semirsuljevic.onboarding.api.welcome.ui.onboarding.RouteOnBoardingPop
import com.semirsuljevic.ui.api.navigation.Navigator
import com.semirsuljevic.ui.api.textfield.TextFieldConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val navigator: Navigator,
    private val hechimAuthentication: HechimAuthentication,
    private val profile: Profile,
    private val profileProvider: ProfileProvider,
    private val loginFormValidator: LoginFormValidator,
    private val permissionsApi: PermissionsApi,
    private val permissionsNavigator: PermissionsNavigator
): ViewModel(){
    private val _resource = mutableStateOf<HechimResource<HechimUser>>(HechimResource.Nothing(""))
    val resource get() = _resource.value

    private val _loginResult = MutableStateFlow<Boolean>(false)
    val loginResult: StateFlow<Boolean> = _loginResult

    private val _emailConfig = mutableStateOf(TextFieldConfig())
    val emailConfig get() = _emailConfig.value

    private val _passwordConfig = mutableStateOf(TextFieldConfig())
    val passwordConfig get() = _passwordConfig.value

    fun onEmailChange(value: String) {
        loginFormValidator.setEmail(value)
    }

    fun onPasswordChange(value: String) {
        loginFormValidator.setPassword(value)
    }

    init {
        setupValidator()
    }

    private fun setupValidator() {
        viewModelScope.launch {
            launch { loginFormValidator.emailConfig.collectLatest { _emailConfig.value = it } }
            launch { loginFormValidator.passwordConfig.collectLatest { _passwordConfig.value = it } }
        }
    }

    fun login() {
        viewModelScope.launch {
            if(!loginFormValidator.validateForm()) {
                return@launch
            }
            _resource.value = HechimResource.Loading("Logging you in")
            delay(1000)
            _resource.value = hechimAuthentication.login(
                email = _emailConfig.value.text,
                password = _passwordConfig.value.text
            )
            if(_resource.value is HechimResource.Success) {
                permissionsNavigator.setRoutes(permissionsApi.checkPermissions())
            }
        }
    }

    fun resetState() {
        _resource.value = HechimResource.Nothing()
    }

    fun navigate() {
        navigator.navigate(RouteOnBoardingPop())
    }

    fun onTrailingClick() = loginFormValidator.invertPasswordVisibility()

    val passwordTrailingIcon get() = if(_passwordConfig.value.visualTransformation == PasswordVisualTransformation())
        R.drawable.ic_hide else R.drawable.ic_show
}
