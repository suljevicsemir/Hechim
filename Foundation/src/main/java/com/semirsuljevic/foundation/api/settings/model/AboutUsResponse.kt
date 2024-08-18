package com.semirsuljevic.foundation.api.settings.model

import kotlinx.serialization.Serializable

@Serializable
data class AboutUsResponse(
    val content: String,
    val date: String
)
