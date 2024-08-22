package com.semirsuljevic.dashboard.internal.di

import com.semirsuljevic.dashboard.api.legal.viewmodel.LegalList
import com.semirsuljevic.dashboard.api.settings.viewmodel.SettingsList
import com.semirsuljevic.dashboard.internal.legal.LegalListImpl
import com.semirsuljevic.dashboard.internal.settings.SettingsListImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class SettingsModule {
    @Binds
    @Singleton
    abstract fun bindSettingsList(impl: SettingsListImpl): SettingsList

    @Binds
    @Singleton
    abstract fun bindLegalList(impl: LegalListImpl): LegalList
}
