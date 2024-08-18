package com.semirsuljevic.onboarding.api.welcome.ui.name.viewmodel

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semirsuljevic.foundation.api.common.HechimResource
import com.semirsuljevic.foundation.api.user.Profile
import com.semirsuljevic.foundation.api.user.ProfileProvider
import com.semirsuljevic.onboarding.api.permissions.PermissionsApi
import com.semirsuljevic.onboarding.api.permissions.navigation.PermissionsNavigator
import com.semirsuljevic.onboarding.api.permissions.viewmodel.PermissionsProviderViewModel
import com.semirsuljevic.onboarding.api.welcome.ui.name.ui.NameFormValidator
import com.semirsuljevic.ui.api.navigation.Navigator
import com.semirsuljevic.ui.api.textfield.TextFieldConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NameViewModel @Inject constructor(
    private val navigator: Navigator,
    private val permissionsNavigator: PermissionsNavigator,
    private val profile: Profile,
    private val permissionsApi: PermissionsApi,
    private val nameFormValidator: NameFormValidator,
    @ApplicationContext private val applicationContext: Context
): PermissionsProviderViewModel(applicationContext){

    private val _firstNameConfig = mutableStateOf(TextFieldConfig())
    val firstNameConfig get() = _firstNameConfig.value

    private val _lastNameConfig = mutableStateOf(TextFieldConfig())
    val lastNameConfig get() = _lastNameConfig.value

    private val _state = mutableStateOf<HechimResource<Boolean>>(HechimResource.Nothing())
    val state get() = _state.value


    fun onFirstNameChange(value: String) { nameFormValidator.setFirstName(value) }
    fun onLastNameChange(value: String) { nameFormValidator.setLastName(value) }


    fun setInfo() {
        viewModelScope.launch {
            _state.value = HechimResource.Loading()
            delay(1000)
            _state.value = profile.updateName(_firstNameConfig.value.text, _lastNameConfig.value.text)
            if (_state.value is HechimResource.Success) {
                permissionsNavigator.setRoutes(permissionsApi.checkPermissions())
            }
        }
    }

    init {
        setupValidator()
    }

    private fun setupValidator() {
        viewModelScope.launch {
            launch { nameFormValidator.firstNameConfig.collectLatest { _firstNameConfig.value = it } }
            launch { nameFormValidator.lastNameConfig.collectLatest { _lastNameConfig.value = it } }
        }
    }

    val buttonEnabled get() = nameFormValidator.formValid.value


}
