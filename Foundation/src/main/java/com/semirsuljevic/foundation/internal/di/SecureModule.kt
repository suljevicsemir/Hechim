package com.semirsuljevic.foundation.internal.di

import com.semirsuljevic.foundation.api.secure.SecureStorage
import com.semirsuljevic.foundation.internal.secure.SecureStorageImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class SecureModule {
    @Binds
    abstract fun bindSecureStorage(
        secureStorageImpl: SecureStorageImpl
    ): SecureStorage
}
