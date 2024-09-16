package com.semirsuljevic.dashboard.api.aboutUs.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semirsuljevic.foundation.api.common.HechimResource
import com.semirsuljevic.foundation.api.settings.HechimSettings
import com.semirsuljevic.foundation.api.settings.model.AboutUsResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AboutUsViewModel @Inject constructor(
    private val hechimSettings: HechimSettings
): ViewModel(){
    private val _aboutUs = mutableStateOf<HechimResource<AboutUsResponse>>(HechimResource.Loading())
    val aboutUs get() = _aboutUs.value

    val aboutUsContent get(): String {
        return when(_aboutUs.value) {
            is HechimResource.Success -> (_aboutUs.value as HechimResource.Success).data.content
            else -> ""
        }
    }


    fun fetchAboutUse() {
        viewModelScope.launch {
            hechimSettings.getAboutUs().collectLatest {
                _aboutUs.value = it
            }
        }
    }
}
