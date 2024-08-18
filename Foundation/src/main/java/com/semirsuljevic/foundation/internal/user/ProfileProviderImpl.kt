package com.semirsuljevic.foundation.internal.user

import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.semirsuljevic.foundation.api.user.model.HechimUser
import com.semirsuljevic.foundation.api.common.HechimError
import com.semirsuljevic.foundation.api.common.HechimResource
import com.semirsuljevic.foundation.api.datastore.HechimDataStore
import com.semirsuljevic.foundation.api.user.ProfileProvider
import com.semirsuljevic.foundation.api.user.model.HechimUserConstants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProfileProviderImpl @Inject constructor(
    private val hechimDataStore: HechimDataStore
): ProfileProvider{
    override suspend fun updateName(firstName: String, lastName: String) {
        val profile = profileFlow.first().copy(
            firstName = firstName,
            lastName = lastName
        )
        hechimDataStore.updateHechimUser(profile)
    }

    override val profileFlow: Flow<HechimUser> get() = hechimDataStore.profileFlow
}
