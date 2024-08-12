package com.semirsuljevic.foundation.internal.di

import com.semirsuljevic.foundation.api.user.Profile
import com.semirsuljevic.foundation.api.user.ProfileProvider
import com.semirsuljevic.foundation.internal.user.ProfileFirebaseImpl
import com.semirsuljevic.foundation.internal.user.ProfileProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UserProfileModule {
    @Binds
    abstract fun bindUserProfile(
        userProfileFirebase: ProfileProviderImpl
    ): ProfileProvider

    @Binds
    abstract fun bindProfile(
        profileFirebaseImpl: ProfileFirebaseImpl
    ): Profile
}
