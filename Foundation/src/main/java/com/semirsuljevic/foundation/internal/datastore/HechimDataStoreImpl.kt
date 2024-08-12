package com.semirsuljevic.foundation.internal.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.semirsuljevic.foundation.api.user.model.HechimUser
import com.semirsuljevic.foundation.api.datastore.HechimDataStore
import com.semirsuljevic.foundation.api.datastore.model.PermissionRequests
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HechimDataStoreImpl @Inject constructor(
    private val context: Context
): HechimDataStore{
    override val Context.permissionRequestsDataStore: DataStore<PermissionRequests> by dataStore(
        fileName = "permission-requests.json",
        serializer = PermissionRequestsSerializer
    )

    override val permissionRequestsFlow: Flow<PermissionRequests> = context.permissionRequestsDataStore.data
    override val Context.profileDataStore: DataStore<HechimUser> by dataStore(
        fileName = "user-profile.json",
        serializer = HechimUserSerializer
    )
    override val profileFlow: Flow<HechimUser> get() = context.profileDataStore.data

    override suspend fun updatePermissionRequests(permissionRequests: PermissionRequests) {
        context.permissionRequestsDataStore.updateData {
            permissionRequests
        }
    }

    override suspend fun updateHechimUser(hechimUser: HechimUser) {
        context.profileDataStore.updateData {
            hechimUser
        }
    }
}
