package com.semirsuljevic.dashboard.api.legal.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.semirsuljevic.foundation.api.common.HechimResource
import com.semirsuljevic.foundation.api.settings.HechimSettings
import com.semirsuljevic.foundation.api.settings.model.AboutUsResponse
import com.semirsuljevic.foundation.api.settings.model.PrivacyPolicyResponse
import com.semirsuljevic.foundation.api.settings.model.TermsOfUseResponse
import com.semirsuljevic.ui.api.common.HechimListItemConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LegalViewModel @Inject constructor(
    private val hechimSettings: HechimSettings,
    private val legalList: LegalList
): ViewModel(){

    private val _terms = mutableStateOf<HechimResource<TermsOfUseResponse>>(HechimResource.Loading())
    val terms get() = _terms.value

    val termsContent get(): String{
        return when(_terms.value) {
            is HechimResource.Success -> {
                (_terms.value as HechimResource.Success).data.content
            }
            else -> {""}
        }
    }

    private val _aboutUs = mutableStateOf<HechimResource<AboutUsResponse>>(HechimResource.Loading())
    val aboutUs get() = _aboutUs.value

    private val _privacyPolicy = mutableStateOf<HechimResource<PrivacyPolicyResponse>>(HechimResource.Loading())
    val privacyPolicy get() = _privacyPolicy.value

    val privacyPolicyContent get(): String{
        return when(_privacyPolicy.value) {
            is HechimResource.Success -> {
                (_privacyPolicy.value as HechimResource.Success).data.content
            }
            else -> {""}
        }
    }

    private val _listItems = mutableStateOf<List<HechimListItemConfig>>(emptyList())
    val listItems get() = _listItems.value

    init { collectListItems() }

    fun fetchTerms() {
        viewModelScope.launch {
            hechimSettings.getTermsOfUse().collectLatest {
                _terms.value = it
            }
        }
    }

    fun fetchPrivacyPolicy() {
        viewModelScope.launch {
            hechimSettings.getPrivacyPolicy().collectLatest {
                _privacyPolicy.value = it
            }
        }
    }

    fun fetchAboutUse() {
        viewModelScope.launch {
            hechimSettings.getAboutUs()
        }
    }

    private fun collectListItems() {
        viewModelScope.launch {
            legalList.legalItems.collectLatest {
                _listItems.value = it
            }
        }
    }
}
