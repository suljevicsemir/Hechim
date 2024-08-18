package com.semirsuljevic.onboarding.api.permissions.config

import com.semirsuljevic.foundation.api.common.UiText

/**
    Util class used to group translation values for Permission screen and
    permission screen's sheet. Used together with PermissionConfig.
 */
data class PermissionString(
    val title: UiText = UiText.StringValue(""),
    val description: UiText = UiText.StringValue(""),
    val nextButton: UiText = UiText.StringValue(""),
    val questionButton: UiText = UiText.StringValue(""),
    val sheetTitle: UiText = UiText.StringValue(""),
    val sheetDescription: UiText = UiText.StringValue(""),
    val sheetButton: UiText = UiText.StringValue("")
)
