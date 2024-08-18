package com.semirsuljevic.foundation.internal.di

import com.semirsuljevic.foundation.api.storage.preferences.AppPreferences
import com.semirsuljevic.foundation.internal.storage.AppPreferencesImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class StorageModule {

    @Binds
    @Singleton
    abstract fun bindCmtPreferences(impl: AppPreferencesImpl): AppPreferences
}
