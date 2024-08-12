package com.semirsuljevic.onboarding.internal.di

import com.semirsuljevic.onboarding.api.permissions.navigation.PermissionsNavigator
import com.semirsuljevic.onboarding.internal.permissions.PermissionsNavigatorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class OnboardingModule {
    @Binds
    abstract fun providePermissionsNavigator(
        permissionsNavigatorImpl: PermissionsNavigatorImpl
    ): PermissionsNavigator
}
