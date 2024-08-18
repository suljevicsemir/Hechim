package com.semirsuljevic.onboarding.api.welcome.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semirsuljevic.foundation.api.authentication.HechimAuthentication
import com.semirsuljevic.foundation.api.common.HechimResource
import com.semirsuljevic.onboarding.api.welcome.ui.login.RouteLogin
import com.semirsuljevic.onboarding.api.welcome.ui.onboarding.RouteOnBoarding
import com.semirsuljevic.onboarding.api.welcome.ui.onboarding.RouteOnBoardingPop
import com.semirsuljevic.onboarding.api.welcome.ui.register.RouteRegister
import com.semirsuljevic.ui.api.navigation.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginEmailViewModel @Inject constructor(
    private val navigator: Navigator,
    private val hechimAuthentication: HechimAuthentication
): ViewModel(){

    private val _resource = mutableStateOf<HechimResource<Boolean>>(HechimResource.Nothing(""))
    val resource: HechimResource<Boolean> get() = _resource.value

    private val _email = mutableStateOf("")
    val email: String get() = _email.value

    fun onEmailChange(value: String) {
        _email.value = value
    }

    /**
        Checks email for authentication.
        If email doesn't exist - redirect to create password page where account will be created.
        If email exists - redirect to password page.
     */
    fun checkEmail() {
        viewModelScope.launch {
            _resource.value = HechimResource.Loading("Checking your email")
            delay(1000)
            val result = hechimAuthentication.checkEmail(_email.value)
            _resource.value = HechimResource.Nothing("")
            navigator.navigate(if(result) RouteLogin() else RouteRegister())
        }
    }

    fun navigate() {
        navigator.navigate(RouteOnBoardingPop())
    }
}
