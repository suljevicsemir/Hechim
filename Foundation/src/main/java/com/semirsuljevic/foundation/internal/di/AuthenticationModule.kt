package com.semirsuljevic.foundation.internal.di

import com.semirsuljevic.foundation.api.authentication.CredentialsValidator
import com.semirsuljevic.foundation.api.authentication.HechimAuthentication
import com.semirsuljevic.foundation.internal.authentication.CredentialsValidatorImpl
import com.semirsuljevic.foundation.internal.authentication.HechimAuthenticationFirebase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AuthenticationModule {
    @Binds
    abstract fun bindAuthenticationApi(
        authenticationImpl: HechimAuthenticationFirebase
    ) : HechimAuthentication

    @Binds
    abstract fun bindCredentialsValidator(
        credentialsValidatorImpl: CredentialsValidatorImpl
    ): CredentialsValidator
}
