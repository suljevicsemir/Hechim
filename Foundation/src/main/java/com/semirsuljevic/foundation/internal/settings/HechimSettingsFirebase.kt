package com.semirsuljevic.foundation.internal.settings

import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.semirsuljevic.foundation.api.common.Dispatchers
import com.semirsuljevic.foundation.api.common.HechimError
import com.semirsuljevic.foundation.api.common.HechimResource
import com.semirsuljevic.foundation.api.common.serialiazers.FirebaseParsing
import com.semirsuljevic.foundation.api.settings.HechimSettings
import com.semirsuljevic.foundation.api.settings.model.AboutUsResponse
import com.semirsuljevic.foundation.api.settings.model.PrivacyPolicyResponse
import com.semirsuljevic.foundation.api.settings.model.TermsOfUseResponse
import com.semirsuljevic.foundation.internal.settings.firebase.SettingsFirebaseConstants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HechimSettingsFirebase @Inject constructor(
    private val firebaseParsing: FirebaseParsing,
    private val dispatchers: Dispatchers
): HechimSettings{
    override fun getAboutUs(): Flow<HechimResource<AboutUsResponse>>{
        CoroutineScope(dispatchers.io).launch {
            delay(1000)
            val task = Firebase.firestore
                .collection(SettingsFirebaseConstants.ABOUT_US_COLLECTION)
                .orderBy("date")
                .limitToLast(1)
                .get()
            task.await()
            if(task.isSuccessful) {
                _aboutUsFlow.tryEmit(HechimResource.Success(
                    firebaseParsing.encodeDocumentToObject(task.result.documents.first())
                    )
                )
            }
        }
        return aboutUsFlow
    }

    override fun getPrivacyPolicy(): Flow<HechimResource<PrivacyPolicyResponse>> {
        CoroutineScope(dispatchers.io).launch {
            delay(1000)
            val task = Firebase.firestore
                .collection(SettingsFirebaseConstants.PRIVACY_POLICY_COLLECTION)
                .orderBy("date")
                .limitToLast(1)
                .get()
            task.await()
            if(task.isSuccessful) {
                _privacyPolicyResponse.tryEmit(HechimResource.Success(
                    firebaseParsing.encodeDocumentToObject<PrivacyPolicyResponse>(task.result.documents.first())
                    )
                )
            }
        }
        return privacyPolicyFlow
    }

    override fun getTermsOfUse(): Flow<HechimResource<TermsOfUseResponse>> {
        CoroutineScope(dispatchers.io).launch {
            delay(1000)
            val task = Firebase.firestore
                .collection(SettingsFirebaseConstants.TERMS_OF_USE_COLLECTION)
                .orderBy("date")
                .limitToLast(1)
                .get()
            task.await()
            if(task.isSuccessful) {
                _termsFlow.tryEmit(HechimResource.Success(
                    firebaseParsing.encodeDocumentToObject<TermsOfUseResponse>(task.result.documents.first()))
                )
            }
        }
        return termsFlow

    }

    override val termsFlow: Flow<HechimResource<TermsOfUseResponse>> get() = _termsFlow.asSharedFlow()
    override val aboutUsFlow: Flow<HechimResource<AboutUsResponse>> get() = _aboutUsFlow.asSharedFlow()
    override val privacyPolicyFlow: Flow<HechimResource<PrivacyPolicyResponse>>
        get() = _privacyPolicyResponse.asSharedFlow()

    private val _termsFlow = MutableSharedFlow<HechimResource<TermsOfUseResponse>>(replay = 1)
    private val _aboutUsFlow = MutableSharedFlow<HechimResource<AboutUsResponse>>(replay = 1)
    private val _privacyPolicyResponse = MutableSharedFlow<HechimResource<PrivacyPolicyResponse>>(replay = 1)
}
