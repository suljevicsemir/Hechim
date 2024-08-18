package com.semirsuljevic.foundation.api.datastore.model

import kotlinx.serialization.Serializable

/**
Model responsible of tracking permission requests (counts them) in order to know will they be
present in permissions flow (if requested more than twice, they won't) or trapdoor flow.
 * @param requests - maps requests per permission, key is manifest permission string
 * @param finishedPermissions - if the user finished permission flow till the end at least once.
Should be cleared after logout. Set to true after navigating to dashboard from PermissionsNavigator
 */
@Serializable
data class PermissionRequests(
    val requests: Map<String, Int> = emptyMap(),
    val finishedPermissions: Boolean
)
