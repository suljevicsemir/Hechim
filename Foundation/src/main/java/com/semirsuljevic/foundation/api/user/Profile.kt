package com.semirsuljevic.foundation.api.user

import com.semirsuljevic.foundation.api.common.HechimResource

/**
    Remote user resource interface.
 */
interface Profile {
    suspend fun getUser()
    suspend fun updateName(firstName: String, lastName: String): HechimResource<Boolean>
}
