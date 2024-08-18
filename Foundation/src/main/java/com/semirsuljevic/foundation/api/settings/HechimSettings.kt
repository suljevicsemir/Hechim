package com.semirsuljevic.foundation.api.settings

import com.semirsuljevic.foundation.api.common.HechimResource
import com.semirsuljevic.foundation.api.settings.model.AboutUsResponse
import com.semirsuljevic.foundation.api.settings.model.PrivacyPolicyResponse
import com.semirsuljevic.foundation.api.settings.model.TermsOfUseResponse
import kotlinx.coroutines.flow.Flow

interface HechimSettings {
    suspend fun getAboutUs()
    suspend fun getPrivacyPolicy()
    suspend fun getTermsOfUse()

    val termsFlow: Flow<HechimResource<TermsOfUseResponse>>
    val aboutUsFlow: Flow<HechimResource<AboutUsResponse>>
    val privacyPolicyFlow: Flow<HechimResource<PrivacyPolicyResponse>>
}
