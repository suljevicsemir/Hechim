package com.semirsuljevic.foundation.internal.authentication

import com.google.android.gms.tasks.Task
import com.google.firebase.Firebase
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.auth
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.firestore
import com.semirsuljevic.foundation.api.authentication.HechimAuthentication
import com.semirsuljevic.foundation.api.user.model.HechimUser
import com.semirsuljevic.foundation.api.common.HechimError
import com.semirsuljevic.foundation.api.common.HechimResource
import com.semirsuljevic.foundation.api.user.ProfileProvider
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class HechimAuthenticationFirebase @Inject constructor(
    private val profile: ProfileProvider,
): HechimAuthentication{
    override fun isAuthenticated() = Firebase.auth.currentUser != null

    override suspend fun checkEmail(email: String): Boolean{
        val task: Task<QuerySnapshot> = Firebase.firestore
            .collection("users")
            .whereEqualTo(
                "email", email
            )
            .get()
        val querySnapshot: QuerySnapshot = task.await()
        return !querySnapshot.isEmpty
    }

    override suspend fun login(email: String, password: String): HechimResource<HechimUser>{
        try {
            val task: Task<AuthResult> = Firebase.auth.signInWithEmailAndPassword(email, password)
            val authResult: AuthResult = task.await()
            return if(authResult.user == null) {
                HechimResource.Error(
                    HechimError(
                        message = "Login failed",
                        buttonTitle = "Try again"
                    )
                )
            }
            else {
                HechimResource.Success(
                    data = HechimUser(
                        id = authResult.user?.uid ?: "",
                        email = authResult.user?.uid ?: ""
                    )
                )
            }
        }
        catch (e: Exception) {
            return HechimResource.Error(
                error = HechimError(
                    message = "Login failed",
                    buttonTitle = "Try again"
                )
            )
        }
    }

    private suspend fun storeUser(
        email: String,
        id: String
    ) {
        val task: Task<Void> = Firebase.firestore
            .collection("users")
            .document(id)
            .set(mapOf(
                    "email" to email,
                )
            )
        task.await()

    }

    override suspend fun register(email: String, password: String): HechimResource<HechimUser> {
        try {
            val task: Task<AuthResult> = Firebase.auth.createUserWithEmailAndPassword(email, password)
            val authResult : AuthResult = task.await()
            return if(authResult.user == null)
                HechimResource.Error(
                    HechimError(
                        message = "Login failed",
                        buttonTitle = "Try again"
                    )
                )
            else {
                login(email, password)
                storeUser(
                    email = email,
                    id = authResult.user?.uid ?: ""
                )
                return HechimResource.Success(
                    data = HechimUser(
                        id = authResult.user?.uid ?: "",
                        email = authResult.user?.uid ?: ""
                    )
                )
            }
        }
        catch (e: Exception) {
            return HechimResource.Error(
                HechimError(
                    message = "Login failed",
                    buttonTitle = "Try again"
                )
            )
        }

    }

    override suspend fun changePassword(oldPassword: String, newPassword: String): HechimResource<Boolean> {
        try {
            val profile = profile.profileFlow.first()
            val authCredential = EmailAuthProvider.getCredential(profile.email, oldPassword)
            var task : Task<Void> = (Firebase.auth.currentUser!!.reauthenticate(authCredential))
            task.await()
            if(task.isSuccessful) {
                val updateTask = Firebase.auth.currentUser!!.updatePassword(newPassword)
                updateTask.await()
                if(updateTask.isSuccessful) {
                    return HechimResource.Success(true)
                }
                return HechimResource.Error(error = HechimError(message = "Update failed"))
            }
            return HechimResource.Error(error = HechimError(message = "Old password incorrect"))
        }
        catch (e: Exception) {
            return HechimResource.Error(error = HechimError(message = "Old password incorrect"))
        }
    }

    override suspend fun logOut() {
        try {
            Firebase.auth.signOut()

        }
        catch (e: Exception) {

        }
    }
}
