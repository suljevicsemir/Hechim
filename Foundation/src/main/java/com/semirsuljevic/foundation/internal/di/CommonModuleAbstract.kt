package com.semirsuljevic.foundation.internal.di

import com.semirsuljevic.foundation.api.authentication.HechimAuthentication
import com.semirsuljevic.foundation.api.common.serialiazers.HechimSerializers
import com.semirsuljevic.foundation.internal.authentication.HechimAuthenticationFirebase
import com.semirsuljevic.foundation.internal.common.HechimSerializersImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class CommonModuleAbstract {
    @Binds
    abstract fun bindHechimSerializers(
        serializersImpl: HechimSerializersImpl
    ) : HechimSerializers
}
