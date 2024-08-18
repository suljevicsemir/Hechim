package com.semirsuljevic.ui.internal.di

import com.semirsuljevic.ui.api.navigation.Navigator
import com.semirsuljevic.ui.internal.navigation.NavigatorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class NavigationModule {
    @Binds
    abstract fun bindNavigator(
        navigatorImpl: NavigatorImpl
    ): Navigator
}
