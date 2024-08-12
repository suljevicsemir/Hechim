package com.semirsuljevic.foundation.api.datastore

import com.semirsuljevic.foundation.api.datastore.model.PermissionRequests
import kotlinx.coroutines.flow.Flow

interface PermissionsRequestsProvider {
    fun getPermissionRequests() : Flow<PermissionRequests>
    suspend fun updatePermissionRequests(value: PermissionRequests)

    /**
    Sets permissions finished flag to true (DataStore storage).
    Called if when user completes permissions flow till the end after authentication.
     */
    suspend fun setPermissionsFinished()
}
