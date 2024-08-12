package com.semirsuljevic.onboarding.internal.permissions

import com.semirsuljevic.foundation.api.datastore.PermissionsRequestsProvider
import com.semirsuljevic.onboarding.api.permissions.navigation.PermissionsNavigator
import com.semirsuljevic.ui.api.navigation.HechimRoute
import com.semirsuljevic.ui.api.navigation.Navigator
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class PermissionsNavigatorImpl @Inject constructor(
    private val navigator: Navigator,
    private val permissionRequestsProvider: PermissionsRequestsProvider
):PermissionsNavigator{
    private var routes: MutableList<HechimRoute> = mutableListOf()

    /**
     * @param permissions - list of permissions which are prompted to user
        if all permissions are granted navigates to Home, otherwise populates routes
        and navigates to first one
     */
    override suspend fun setRoutes(permissions: List<String>) {
        if(permissions.isEmpty()) {
            //set permissions completed
            permissionRequestsProvider.setPermissionsFinished()
            //navigate to home
            navigator.navigateHome()
            return
        }
        routes.clear()
        permissions.forEach {
            routes.add(PermissionRoute(permissionType = PermissionType.fromManifestPermission(it)))
        }
        next()
    }

    /**
        Navigates to next permission in order, or to Home page if no permissions are present.
     */
    override suspend fun next() {
        if(routes.isEmpty()) {
            permissionRequestsProvider.setPermissionsFinished()
            navigator.navigateHome()
            return
        }
        navigator.navigateAndRemove(routes.first())
        routes.removeFirst()
    }
}
