package com.semirsuljevic.foundation.internal.di

import com.semirsuljevic.foundation.api.workouts.WorkoutsApi
import com.semirsuljevic.foundation.internal.workouts.WorkoutsApiImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class WorkoutsModule {
    @Binds
    @Singleton
    abstract fun bindCmtPreferences(impl: WorkoutsApiImpl): WorkoutsApi
}
