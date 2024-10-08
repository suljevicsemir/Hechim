package com.semirsuljevic.dashboard.internal.di

import com.semirsuljevic.dashboard.api.applicationSettings.viewmodel.ApplicationSettingsList
import com.semirsuljevic.dashboard.api.changePassword.viewmodel.ChangePasswordFormValidator
import com.semirsuljevic.dashboard.api.legal.viewmodel.LegalList
import com.semirsuljevic.dashboard.api.settings.viewmodel.SettingsList
import com.semirsuljevic.dashboard.internal.applicationSettings.ApplicationSettingsListImpl
import com.semirsuljevic.dashboard.internal.changePassword.ChangePasswordFormValidatorImpl
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

    @Binds
    @Singleton
    abstract fun bindApplicationSettingsList(impl: ApplicationSettingsListImpl): ApplicationSettingsList

    @Binds
    abstract fun bindChangePasswordFormValidator(impl: ChangePasswordFormValidatorImpl): ChangePasswordFormValidator
}
