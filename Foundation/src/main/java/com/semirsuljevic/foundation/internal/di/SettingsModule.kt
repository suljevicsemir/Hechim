package com.semirsuljevic.foundation.internal.di

import com.semirsuljevic.foundation.api.common.serialiazers.FirebaseParsing
import com.semirsuljevic.foundation.api.common.serialiazers.HechimSerializers
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SettingsModule {

    @Singleton
    @Provides
    fun provideFirebaseModule(serializers: HechimSerializers) = FirebaseParsing(serializers)
}
