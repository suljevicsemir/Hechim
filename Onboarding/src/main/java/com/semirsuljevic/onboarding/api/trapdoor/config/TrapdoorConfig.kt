package com.semirsuljevic.onboarding.api.trapdoor.config

import com.semirsuljevic.foundation.api.common.UiText

/**
    Trapdoor config class, used together with TrapdoorString.
    Holds keys of each trapdoor screen, as defined in TrapdoorConstants.
 */
data class TrapdoorConfig(
    val icon: Int,
    val title: UiText,
    val description: UiText,
    val steps: UiText,
    val button: UiText,
    val manifestPermission: String = ""
)
