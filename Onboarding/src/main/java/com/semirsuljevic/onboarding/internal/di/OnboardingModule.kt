package com.semirsuljevic.onboarding.internal.di

import com.semirsuljevic.onboarding.api.permissions.PermissionsApi
import com.semirsuljevic.onboarding.api.permissions.navigation.PermissionsNavigator
import com.semirsuljevic.onboarding.api.welcome.ui.login.LoginFormValidator
import com.semirsuljevic.onboarding.api.welcome.ui.name.ui.NameFormValidator
import com.semirsuljevic.onboarding.api.welcome.ui.register.RegisterFormValidator
import com.semirsuljevic.onboarding.internal.permissions.PermissionsApiImpl
import com.semirsuljevic.onboarding.internal.permissions.PermissionsNavigatorImpl
import com.semirsuljevic.onboarding.internal.welcome.login.LoginFormValidatorImpl
import com.semirsuljevic.onboarding.internal.welcome.name.NameFormValidatorImpl
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
    abstract fun providePermissionsApi(impl: PermissionsApiImpl): PermissionsApi

    @Binds
    abstract fun provideRegisterFormValidator(impl: RegisterFormValidatorImpl): RegisterFormValidator
    @Binds
    abstract fun provideNameFormValidator(impl: NameFormValidatorImpl): NameFormValidator
    @Binds
    abstract fun provideLoginFormValidator(impl: LoginFormValidatorImpl): LoginFormValidator
}
