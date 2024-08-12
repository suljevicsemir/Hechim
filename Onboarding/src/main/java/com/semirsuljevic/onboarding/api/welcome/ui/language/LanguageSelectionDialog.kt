package com.semirsuljevic.onboarding.api.welcome.ui.language

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.hechimdemo.onboarding.R
import com.semirsuljevic.foundation.api.common.UiText
import com.semirsuljevic.ui.api.dialog.HechimDialog
import com.semirsuljevic.ui.api.dialog.HechimDialogConfig

@Composable
internal fun LanguageSelectionDialog(
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    visible: Boolean,
    language: String
) {
    HechimDialog(
        config = HechimDialogConfig(
            onConfirm = onConfirm,
            confirmButton = UiText.StringResource(R.string.common_yes),
            onDismiss = onDismiss,
            dismissButton = UiText.StringResource(R.string.common_no),
            title = UiText.StringResource(R.string.language_selection_alert_title),
            description = UiText.StringResource(R.string.language_selection_alert_content, language),
            visible = visible,
        )
    )
}

@Preview
@Composable
fun LanguageSelectionDialogPreview() {
    LanguageSelectionDialog(
        onConfirm = {},
        onDismiss = {},
        visible = true,
        language = "English"
    )
}
