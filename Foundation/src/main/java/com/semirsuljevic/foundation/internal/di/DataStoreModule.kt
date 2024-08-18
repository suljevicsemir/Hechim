package com.semirsuljevic.foundation.internal.di

import com.semirsuljevic.foundation.api.datastore.HechimDataStore
import com.semirsuljevic.foundation.api.datastore.PermissionsRequestsProvider
import com.semirsuljevic.foundation.internal.datastore.HechimDataStoreImpl
import com.semirsuljevic.foundation.internal.datastore.PermissionsRequestsProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataStoreModule {
    @Binds
    @Singleton
    abstract fun bindHechimDataStore(
        hechimDataStoreImpl: HechimDataStoreImpl
    ): HechimDataStore

    @Binds
    @Singleton
    abstract fun bindPermissionRequestsProvider(
        permissionsRequestsProviderImpl: PermissionsRequestsProviderImpl
    ): PermissionsRequestsProvider
}
