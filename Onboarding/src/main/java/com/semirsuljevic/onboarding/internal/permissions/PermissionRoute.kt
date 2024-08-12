package com.semirsuljevic.onboarding.internal.permissions

import com.semirsuljevic.onboarding.api.permissions.config.PermissionConstants
import com.semirsuljevic.ui.api.navigation.HechimRoute

internal class PermissionRoute(val permissionType: PermissionType):
    HechimRoute("/${permissionType.name}/${PermissionConstants.permissionRoute}")
