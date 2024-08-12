package com.semirsuljevic.foundation.api.authentication

import com.semirsuljevic.foundation.api.user.model.HechimUser
import com.semirsuljevic.foundation.api.common.HechimResource

interface HechimAuthentication {

    fun isAuthenticated(): Boolean
    suspend fun checkEmail(email: String): Boolean
    suspend fun login(email: String, password: String): HechimResource<HechimUser>
    suspend fun register(email: String, password: String): HechimResource<HechimUser>

    suspend fun changePassword(oldPassword: String, newPassword: String): HechimResource<Boolean>
    suspend fun logOut()
}
