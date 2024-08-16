package com.semirsuljevic.onboarding.internal.di

import com.semirsuljevic.onboarding.api.permissions.navigation.PermissionsNavigator
import com.semirsuljevic.onboarding.api.welcome.ui.register.RegisterFormValidator
import com.semirsuljevic.onboarding.internal.permissions.PermissionsNavigatorImpl
import com.semirsuljevic.onboarding.internal.welcome.register.RegisterFormValidatorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class OnboardingModule {
    @Binds
    abstract fun providePermissionsNavigator(permissionsNavigatorImpl: PermissionsNavigatorImpl): PermissionsNavigator

    @Binds
    abstract fun provideRegisterFormValidator(impl: RegisterFormValidatorImpl): RegisterFormValidator
}
