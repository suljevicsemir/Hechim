package com.semirsuljevic.dashboard.internal.di

import com.semirsuljevic.dashboard.api.navigation.DashboardNavigator
import com.semirsuljevic.dashboard.api.settings.viewmodel.SettingsList
import com.semirsuljevic.dashboard.internal.navigation.DashboardNavigatorImpl
import com.semirsuljevic.dashboard.internal.settings.SettingsListImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DashboardModule {
    @Binds
    @Singleton
    abstract fun bindDashboardNavigator(impl: DashboardNavigatorImpl): DashboardNavigator

    @Binds
    @Singleton
    abstract fun bindSettingsList(impl: SettingsListImpl): SettingsList
}
