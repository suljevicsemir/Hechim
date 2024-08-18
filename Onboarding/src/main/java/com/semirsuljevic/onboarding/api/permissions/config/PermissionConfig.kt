package com.semirsuljevic.onboarding.api.permissions.config

import com.semirsuljevic.onboarding.internal.permissions.PermissionType

/**
Util class used to group configuration of Permission screen values (keys of strings).
Used together with PermissionString
 */
data class PermissionConfig(
    val titleKey: Int,
    val descriptionKey: Int,
    val permissionType : PermissionType,
    val manifestPermission: String,
    val multiplePermissions: List<String>? = null,
    val nextButtonKey: Int,
    val questionButtonString: Int,
    val image: Int,
    val sheetTitleKey: Int,
    val sheetDescriptionKey: Int,
    val sheetButtonKey: Int
)
