package com.semirsuljevic.onboarding.api.permissions.navigation

interface PermissionsNavigator {
    suspend fun setRoutes(permissions: List<String>)
    suspend fun next()
}
