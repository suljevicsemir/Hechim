package com.semirsuljevic.foundation.internal.user

import com.semirsuljevic.foundation.api.common.HechimResource
import com.semirsuljevic.foundation.api.user.Profile
import com.semirsuljevic.foundation.api.user.ProfileProvider
import javax.inject.Inject

class ProfileApiImpl @Inject constructor(
    private val profileProvider: ProfileProvider
): Profile{
    override suspend fun getUser() {
        TODO("Not yet implemented")
    }

    override suspend fun updateName(firstName: String, lastName: String): HechimResource<Boolean> {
        TODO("Not yet implemented")
    }
}
