package com.semirsuljevic.ui.api.dialog

import com.semirsuljevic.foundation.api.common.UiText

data class HechimDialogConfig(
    val title: UiText,
    val description: UiText,
    val onConfirm: () -> Unit,
    val onDismiss: () -> Unit,
    val dismissButton: UiText,
    val confirmButton: UiText,
    val visible: Boolean
)
