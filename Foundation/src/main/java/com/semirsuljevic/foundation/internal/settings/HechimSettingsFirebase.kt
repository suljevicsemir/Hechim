package com.semirsuljevic.foundation.internal.settings

import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.semirsuljevic.foundation.api.common.HechimError
import com.semirsuljevic.foundation.api.common.HechimResource
import com.semirsuljevic.foundation.api.common.serialiazers.FirebaseParsing
import com.semirsuljevic.foundation.api.common.serialiazers.model.FirebaseTask
import com.semirsuljevic.foundation.api.settings.HechimSettings
import com.semirsuljevic.foundation.api.settings.model.AboutUsResponse
import com.semirsuljevic.foundation.api.settings.model.PrivacyPolicyResponse
import com.semirsuljevic.foundation.api.settings.model.TermsOfUseResponse
import com.semirsuljevic.foundation.internal.settings.firebase.SettingsFirebaseConstants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HechimSettingsFirebase @Inject constructor(
    private val firebaseParsing: FirebaseParsing
): HechimSettings{
    override suspend fun getAboutUs() {
        val task = Firebase.firestore
            .collection(SettingsFirebaseConstants.ABOUT_US_COLLECTION)
            .orderBy("date")
            .limitToLast(1)
            .get()
        task.await()
        if(task.isSuccessful) {
            val doc = task.result.documents.first()
            _aboutUsFlow.tryEmit(
                HechimResource.Success(
                    AboutUsResponse(
                        content = doc.get("content") as String,
                        date = doc.get("date") as String
                    )
                )
            )
            return
        }
        _aboutUsFlow.tryEmit(HechimResource.Error(error = HechimError(message = "")))
    }

    override suspend fun getPrivacyPolicy() {
        val task = Firebase.firestore
            .collection(SettingsFirebaseConstants.PRIVACY_POLICY_COLLECTION)
            .orderBy("date")
            .limitToLast(1)
            .get()
        task.await()
        if(task.isSuccessful) {
            val doc = task.result.documents.first()
            _privacyPolicyResponse.tryEmit(
                HechimResource.Success(
                    PrivacyPolicyResponse(
                        content = doc.get("content") as String,
                        date = doc.get("date") as String
                    )
                )
            )
            return
        }
        _privacyPolicyResponse.tryEmit(HechimResource.Error(error = HechimError(message = "")))
    }

    override suspend fun getTermsOfUse() {
        val task = Firebase.firestore
            .collection(SettingsFirebaseConstants.TERMS_OF_USE_COLLECTION)
            .orderBy("date")
            .limitToLast(1)
            .get()
        task.await()
        if(task.isSuccessful) {
            val doc = task.result.documents.first()
            _termsFlow.tryEmit(
                HechimResource.Success(
                    TermsOfUseResponse(
                        content = doc.get("content") as String,
                        date = doc.get("date") as String
                    )
                )
            )
            return
        }
        _termsFlow.tryEmit(HechimResource.Error(error = HechimError(message = "")))
    }

    override val termsFlow: Flow<HechimResource<TermsOfUseResponse>> get() = _termsFlow.asSharedFlow()
    override val aboutUsFlow: Flow<HechimResource<AboutUsResponse>> get() = _aboutUsFlow.asSharedFlow()
    override val privacyPolicyFlow: Flow<HechimResource<PrivacyPolicyResponse>>
        get() = _privacyPolicyResponse.asSharedFlow()

    private val _termsFlow = MutableSharedFlow<HechimResource<TermsOfUseResponse>>(replay = 1)
    private val _aboutUsFlow = MutableSharedFlow<HechimResource<AboutUsResponse>>(replay = 1)
    private val _privacyPolicyResponse = MutableSharedFlow<HechimResource<PrivacyPolicyResponse>>(replay = 1)
}
