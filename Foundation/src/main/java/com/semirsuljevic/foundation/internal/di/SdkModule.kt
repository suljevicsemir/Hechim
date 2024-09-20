package com.semirsuljevic.foundation.internal.di

import com.semirsuljevic.foundation.api.sdk.TrackerNotificationManager
import com.semirsuljevic.foundation.api.sdk.TrackerServiceManager
import com.semirsuljevic.foundation.internal.sdk.TrackerNotificationManagerImpl
import com.semirsuljevic.foundation.internal.sdk.TrackerServiceManagerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class SdkModule {
    @Binds
    abstract fun bindTrackerServiceManager(impl: TrackerServiceManagerImpl) : TrackerServiceManager

    @Binds
    abstract fun bindTrackerNotificationManager(impl: TrackerNotificationManagerImpl): TrackerNotificationManager
}
