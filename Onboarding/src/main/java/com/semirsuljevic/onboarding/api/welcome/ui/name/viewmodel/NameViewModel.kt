package com.semirsuljevic.onboarding.api.welcome.ui.name.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semirsuljevic.foundation.api.common.HechimResource
import com.semirsuljevic.foundation.api.user.Profile
import com.semirsuljevic.foundation.api.user.ProfileProvider
import com.semirsuljevic.ui.api.navigation.Navigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NameViewModel @Inject constructor(
    private val profileProvider: ProfileProvider,
    private val navigator: Navigator,
    private val profile: Profile
): ViewModel(){

    private val _firstName = mutableStateOf("")
    val firstName get() = _firstName.value

    private val _lastName = mutableStateOf("")
    val lastName get() = _lastName.value

    private val _state = mutableStateOf<HechimResource<Boolean>>(HechimResource.Nothing())
    val state get() = _state.value


    fun onFirstNameChange(value: String) { _firstName.value = value }
    fun onLastNameChange(value: String) { _lastName.value = value }


    fun setInfo() {
        viewModelScope.launch {
            _state.value = HechimResource.Loading()
            _state.value = profile.updateName(_firstName.value, _lastName.value)
            if (_state.value is HechimResource.Success) {
                navigator.navigateHome()
            }
        }
    }

    val buttonEnabled get() = _firstName.value.isNotEmpty() && _lastName.value.isNotEmpty()


}
