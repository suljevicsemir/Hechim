package com.semirsuljevic.foundation.api.settings

import com.semirsuljevic.foundation.api.common.HechimResource
import com.semirsuljevic.foundation.api.settings.model.AboutUsResponse
import com.semirsuljevic.foundation.api.settings.model.PrivacyPolicyResponse
import com.semirsuljevic.foundation.api.settings.model.TermsOfUseResponse
import kotlinx.coroutines.flow.Flow

interface HechimSettings {
    fun getAboutUs(): Flow<HechimResource<AboutUsResponse>>
    fun getPrivacyPolicy(): Flow<HechimResource<PrivacyPolicyResponse>>
    fun getTermsOfUse(): Flow<HechimResource<TermsOfUseResponse>>

    val termsFlow: Flow<HechimResource<TermsOfUseResponse>>
    val aboutUsFlow: Flow<HechimResource<AboutUsResponse>>
    val privacyPolicyFlow: Flow<HechimResource<PrivacyPolicyResponse>>
}
