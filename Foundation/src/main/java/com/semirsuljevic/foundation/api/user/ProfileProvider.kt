package com.semirsuljevic.foundation.api.user

import com.semirsuljevic.foundation.api.user.model.HechimUser
import com.semirsuljevic.foundation.api.common.HechimResource
import kotlinx.coroutines.flow.Flow

interface ProfileProvider {
    suspend fun updateName(firstName: String, lastName: String)

    val profileFlow: Flow<HechimUser>
}
