package com.semirsuljevic.foundation.internal.di

import com.semirsuljevic.foundation.api.settings.HechimSettings
import com.semirsuljevic.foundation.internal.settings.HechimSettingsFirebase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class SettingsModuleAbstract {
    @Binds
    abstract fun bindSettings(
        hechimSettingsFirebase: HechimSettingsFirebase
    ): HechimSettings
}
