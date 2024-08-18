package com.semirsuljevic.foundation.internal.user

import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.semirsuljevic.foundation.api.common.HechimError
import com.semirsuljevic.foundation.api.common.HechimResource
import com.semirsuljevic.foundation.api.datastore.HechimDataStore
import com.semirsuljevic.foundation.api.user.Profile
import com.semirsuljevic.foundation.api.user.ProfileProvider
import com.semirsuljevic.foundation.api.user.model.HechimUser
import com.semirsuljevic.foundation.api.user.model.HechimUserConstants
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ProfileFirebaseImpl @Inject constructor(
    private val profileProvider: ProfileProvider,
    private val hechimDataStore: HechimDataStore
): Profile {
    override suspend fun getUser() {
        val task = Firebase.firestore
            .collection(HechimUserConstants.FIREBASE_USER_COLLECTION)
            .document(Firebase.auth.uid ?: "")
            .get()
        task.await()
        if(task.isSuccessful) {
            val profile = task.result.toObject(HechimUser::class.java)!!
            hechimDataStore.updateHechimUser(hechimUser = profile)
        }
    }

    override suspend fun updateName(firstName: String, lastName: String):HechimResource<Boolean> {
        val task = Firebase.firestore
            .collection(HechimUserConstants.FIREBASE_USER_COLLECTION)
            .document(Firebase.auth.uid ?: "")
            .update(mapOf(
                HechimUserConstants.FIRST_NAME_KEY to firstName,
                HechimUserConstants.LAST_NAME_KEY to lastName
            ))
        task.await()
        if(task.isSuccessful) {
            profileProvider.updateName(firstName, lastName)
            return HechimResource.Success(true)
        }
        return HechimResource.Error(HechimError(message = ""))
    }
}
