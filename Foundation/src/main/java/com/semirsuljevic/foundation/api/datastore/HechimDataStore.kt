package com.semirsuljevic.foundation.api.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import com.semirsuljevic.foundation.api.user.model.HechimUser
import com.semirsuljevic.foundation.api.datastore.model.PermissionRequests
import kotlinx.coroutines.flow.Flow

interface HechimDataStore {

    val Context.permissionRequestsDataStore: DataStore<PermissionRequests>
    val permissionRequestsFlow: Flow<PermissionRequests>

    val Context.profileDataStore: DataStore<HechimUser>
    val profileFlow: Flow<HechimUser>

    suspend fun updatePermissionRequests(permissionRequests: PermissionRequests)

    suspend fun updateHechimUser(hechimUser: HechimUser)
}
