package com.semirsuljevic.foundation.api.settings.model

import kotlinx.serialization.Serializable

@Serializable
data class PrivacyPolicyResponse(
    val content: String,
    val date: String = ""
)
