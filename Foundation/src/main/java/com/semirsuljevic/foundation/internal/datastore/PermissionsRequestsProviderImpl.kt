package com.semirsuljevic.foundation.internal.datastore

import com.semirsuljevic.foundation.api.datastore.HechimDataStore
import com.semirsuljevic.foundation.api.datastore.PermissionsRequestsProvider
import com.semirsuljevic.foundation.api.datastore.model.PermissionRequests
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class PermissionsRequestsProviderImpl @Inject constructor(
    private val hechimDataStore: HechimDataStore
): PermissionsRequestsProvider{
    override fun getPermissionRequests(): Flow<PermissionRequests> = hechimDataStore.permissionRequestsFlow

    override suspend fun updatePermissionRequests(value: PermissionRequests) {
        hechimDataStore.updatePermissionRequests(value)
    }

    override suspend fun setPermissionsFinished() {
        val requests = getPermissionRequests().first()
        updatePermissionRequests(PermissionRequests(
            requests.requests,
            true
         )
        )
    }
}
